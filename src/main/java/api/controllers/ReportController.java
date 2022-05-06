package api.controllers;

import api.GlobalValues;
import api.helpers.request.ReportRequestHelper;
import api.helpers.request.UseridBodyHelper;
import api.helpers.response.ReportResponseHelper;
import api.middlewares.UserMiddlewares;
import api.services.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import src.main.java.model.tables.pojos.Reports;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {
    @Autowired
    ReportService reportService;

    @Operation(summary= "Deletes a report.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item destroyed"),
            @ApiResponse(responseCode = "400", description = "Some parameter does not have a valid value", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough permissions", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item doesn't exists", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteReport(@PathVariable Integer id, @RequestBody UseridBodyHelper useridBody) throws SQLException {
        UserMiddlewares.isAdmin(useridBody.userid());
        reportService.deleteReport(id);
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }


    @Operation(summary = "Get all reports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search successful")})
    @GetMapping
    public List<Reports> getAllReports(@RequestParam(name="page", required = false) String pageParam, @RequestBody UseridBodyHelper user) throws SQLException{
        UserMiddlewares.isAdmin(user.userid());
        Integer page = pageParam!=null ? Integer.valueOf(pageParam) : 1;
        Integer offset = page * GlobalValues.PAGE_SIZE - GlobalValues.PAGE_SIZE;
        return ReportService.getAllReports(offset);
    }

    @PostMapping
    public ResponseEntity<ReportResponseHelper> postReport(@RequestBody ReportRequestHelper report) throws SQLException {
        if(report.useridreported() == null || report.reviewid() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reportService.postReport(report), HttpStatus.CREATED);
    }

}
