package com.example.navigationmodule.internal

import com.example.navigationmodule.Route
import com.example.navigationmodule.Router

internal object EmptyRouter: Router {
    override fun launch(route: Route) = Unit

    override fun pop() = Unit

    override fun restart(route: Route) = Unit

}