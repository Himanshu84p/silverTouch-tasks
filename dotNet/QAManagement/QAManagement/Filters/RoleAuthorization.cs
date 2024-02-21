using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace QAManagement.Filters
{
    public class RoleAuthorizationAttribute : FilterAttribute, IAuthorizationFilter
    {
        private readonly string[] _rolesAllowed;

        public RoleAuthorizationAttribute(params string[] rolesAllowed)
        {
            _rolesAllowed = rolesAllowed;
        }

        public void OnAuthorization(AuthorizationContext filterContext)
        {
            var userRole = (string)filterContext.HttpContext.Session["UserRole"]; // Assuming you store the user role in session
            bool isAuthorized = Array.Exists(_rolesAllowed, role => role.Equals(userRole, StringComparison.OrdinalIgnoreCase));

            if (!isAuthorized)
            {
                
                filterContext.Result = new ViewResult
                {
                    ViewName = "UnauthorizedAccess",
                    ViewData = filterContext.Controller.ViewData,
                    TempData = filterContext.Controller.TempData
                };

            }
        }
    }
}