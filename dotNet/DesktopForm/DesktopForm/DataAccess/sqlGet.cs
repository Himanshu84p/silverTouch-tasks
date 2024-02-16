using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DesktopForm.DataAccess
{
    public static class sqlGet
    {
        public static DataSet getDataset(string cmdName, CommandType cmdType)
        {
            string sqlConnstr = ConfigurationManager.ConnectionStrings["DesktopForm.Properties.Settings.StudentWinFormConnectionString"].ConnectionString;
            SqlConnection sqlConn = new SqlConnection(sqlConnstr);

            SqlCommand sqlCmd = sqlConn.CreateCommand();
            sqlCmd.CommandText = cmdName;
            sqlCmd.CommandType = cmdType;

            DataSet ds = new DataSet();

            SqlDataAdapter da = new SqlDataAdapter(sqlCmd);
            sqlConn.Open();
            da.Fill(ds);
            sqlConn.Close();

            return ds;

        }

        public static void executeNonQuery(string cmdName, CommandType cmdType)
        {
            string sqlConnstr = ConfigurationManager.ConnectionStrings["DesktopForm.Properties.Settings.StudentWinFormConnectionString"].ConnectionString;
            SqlConnection sqlConn = new SqlConnection(sqlConnstr);

            SqlCommand sqlCmd = sqlConn.CreateCommand();
            sqlCmd.CommandText = cmdName;
            sqlCmd.CommandType = cmdType;

            sqlConn.Open();
            sqlCmd.ExecuteNonQuery();
            sqlConn.Close();

        }
    }
}
