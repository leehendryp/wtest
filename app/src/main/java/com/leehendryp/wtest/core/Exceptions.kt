package com.leehendryp.wtest.core

class MyBadException(message: String?) : Throwable(message)
class ServiceInstabilityException(message: String) : Throwable(message)
