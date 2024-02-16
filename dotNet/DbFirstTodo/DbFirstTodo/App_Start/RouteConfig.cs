using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;

namespace DbFirstTodo
{
    public class RouteConfig
    {
        public static void RegisterRoutes(RouteCollection routes)
        {
            routes.IgnoreRoute("{resource}.axd/{*pathInfo}");

            routes.MapRoute(
            name: "complete",
            url: "Todo/Complete/{id}/{isCompleted}",
            defaults: new { controller = "Todo", action = "Complete" }
        );
            routes.MapRoute(
                name: "Todo List Route",
                url: "Todo/{action}/{id}",
                defaults: new { controller = "Todo", action = "Index", id = UrlParameter.Optional }
            );
            routes.MapRoute(
                name: "Default",
                url: "{controller}/{action}/{id}",
                defaults: new { controller = "Home", action = "Index", id = UrlParameter.Optional }
            );
        }
    }
}
