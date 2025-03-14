package com.watchdognet.catalog_service

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<CatalogServiceApplication>().with(TestcontainersConfiguration::class).run(*args)
}
