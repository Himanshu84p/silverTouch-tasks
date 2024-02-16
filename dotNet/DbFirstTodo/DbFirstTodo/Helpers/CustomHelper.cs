using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace DbFirstTodo.Helpers
{
    public static class CustomHelper
    {
        public static MvcHtmlString DisplayTempData(this HtmlHelper htmlHelper, string key, string successMessage, string successColor)
        {
            if (htmlHelper.ViewContext.TempData[key] != null && htmlHelper.ViewContext.TempData[key].ToString() == successMessage)
            {
                TagBuilder tagBuilder = new TagBuilder("p");
                tagBuilder.MergeAttribute("style", $"color:{successColor};");
                tagBuilder.SetInnerText(successMessage);
                return MvcHtmlString.Create(tagBuilder.ToString());
            }
            return MvcHtmlString.Empty;
        }
    }
}