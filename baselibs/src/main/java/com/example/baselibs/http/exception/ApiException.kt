package com.example.baselibs.http.exception

import java.io.IOException

class ApiException(val statusCode: Int, message: String?) : IOException(message)