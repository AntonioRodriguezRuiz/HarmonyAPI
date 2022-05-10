<p style="text-align: center; width: initial">
    <img width="66%" src="./assets/harmony.gif" alt="Animated Harmony logo"> 
</p>

<h1 style="text-align: center">Harmony</h1>
<p style="text-align: center">Harmony API Java server</p>

# Index
* [Quick start guide](#quick-start-guide)
* [Usage suggestions](#usage-suggestions)
* [Local deployment](#local-deployment)


# Quick start guide
To check out URIs and request bodies, refer to our [SwaggerHub Page](https://harmonyapi.rocks).
The base URI is `https://harmonyapi.rocks/api/v1`.

When administrator permissions are needed for an operation, use `userid = 1` in the request, or else you'll meet a 403 Forbidden error.

# Usage suggestions

# Local deployment
If for some reason you'd rather do operations on a locally hosted version of the API, follow these steps. 
We strongly suggest the use of IntelliJ IDEA. It is free for us as part of the GitHub Student package. Use [this form](https://www.jetbrains.com/shop/eform/students).

All POJO classes inside the src.main.java.model package are generated from the Harmony database. 
These are provided from the repository and updated if the model changes.

In any case, you can regenerate them if you want to. To do so, first delete the src/main/java/model directory. After that, in the Maven sidebar of IntelliJ, run `harmony-api` > `Lifecyle` > `install`.


