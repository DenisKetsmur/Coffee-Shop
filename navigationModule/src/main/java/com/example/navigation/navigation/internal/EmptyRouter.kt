package com.example.navigation.navigation.internal

import com.example.navigation.navigation.Route
import com.example.navigation.navigation.Router

internal object EmptyRouter: Router {
    override fun launch(route: Route) = Unit

    override fun pop() = Unit

    override fun restart(route: Route) = Unit

}