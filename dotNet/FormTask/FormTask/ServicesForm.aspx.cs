using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Diagnostics;
using System.Web.Services.Description;

namespace FormTask
{
    public partial class ServicesForm : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                BindGrid();
            }
            GridView1.EditIndex = -1;
            BindGrid();
        }

        private void BindGrid()
        {
            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;
            string query = "SELECT * FROM Service";
            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlDataAdapter sda = new SqlDataAdapter(query, con))
                {
                    using (DataTable dt = new DataTable())
                    {
                        sda.Fill(dt);
                        GridView1.DataSource = dt;
                        GridView1.DataBind();
                    }
                }
            }
        }

        protected void btnSubmit_Click(object sender, EventArgs e)
        {
            
            string ServiceName = inpServiceName.Text;
            int DepartmentID = Convert.ToInt32( inpDepartmentID.Text);
            string Description = inpDescription.Text;
            string Price = inpPrice.Text;
            string Date = inpDate.Text;
            string Available = inpAvailable.Text;

            inpDepartmentID.ClearSelection();
            inpAvailable.Text = "";
            inpServiceName.Text = "";
            inpDescription.Text = "";
            inpPrice.Text = "";
            inpDate.Text = "";

            string query = "INSERT INTO Service VALUES(@ServiceName, @DepartmentID, @Description, @Price, @Date,@Available)";
            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;
            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@DepartmentID", DepartmentID);
                    cmd.Parameters.AddWithValue("@ServiceName", ServiceName);
                    cmd.Parameters.AddWithValue("@Description", Description);
                    cmd.Parameters.AddWithValue("@Price", Price);
                    cmd.Parameters.AddWithValue("@Date", Date);
                    cmd.Parameters.AddWithValue("@Available", Available);
                    cmd.Connection = con;
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
            }
            this.BindGrid();
        }

        protected void OnRowEditing(object sender, GridViewEditEventArgs e)
        {
            GridViewRow row = GridView1.Rows[e.NewEditIndex];

            int ServiceID = Convert.ToInt32(GridView1.DataKeys[e.NewEditIndex].Value);

            string ServiceName = (row.FindControl("lblServiceName") as Label).Text;

            int DepartmentID = Convert.ToInt32( (row.FindControl("lblDepartmentID") as Label).Text);

            string Description = (row.FindControl("lblDescription") as Label).Text;

            string Price = (row.FindControl("lblPrice") as Label).Text;

            string Date = (row.FindControl("lblDate") as Label).Text;
            DateTime dateTime = DateTime.Parse(Date.ToString());
            string Available = (row.FindControl("lblAvailable") as Label).Text;

            tempID.Text= ServiceID.ToString();
            inpServiceName.Text = ServiceName;
            inpDepartmentID.Text = DepartmentID.ToString();
            inpDescription.Text = Description;
            inpPrice.Text = Price.ToString();
            inpDate.Text = dateTime.ToString("yyyy-MM-dd");
            inpAvailable.Text = Available;

            btnUpdate.Visible = true;
            btnCancel.Visible = true;
            btnSubmit.Visible = false;


            this.BindGrid();
        }

        protected void btnCancel_Click(object sender, EventArgs e)
        {
            inpServiceName.Text = "";
            inpDepartmentID.ClearSelection();
            inpDescription.Text = "";
            inpPrice.Text = "";
            inpDate.Text = "";
            inpAvailable.Text = "";

            btnUpdate.Visible = false;
            btnCancel.Visible = false;

            btnSubmit.Visible = true;

            GridView1.EditIndex = -1;
            this.BindGrid();
        }

        protected void btnUpdate_Click(object sender, EventArgs e)
        {
            string ServiceID = tempID.Text;
            string ServiceName = inpServiceName.Text;
            int DepartmentID = Convert.ToInt32(inpDepartmentID.Text);
            string Description = inpDescription.Text;
            string Price = inpPrice.Text;
            string Date = inpDate.Text;
            string Available = inpAvailable.Text;



            string query = "UPDATE Service SET ServiceName=@ServiceName, DepartmentID=@DepartmentID, Description=@Description, Price=@Price , Date=@Date, Available=@Available WHERE ServiceID=@ServiceID";

            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;

            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@ServiceName", ServiceName);
                    cmd.Parameters.AddWithValue("@DepartmentID", DepartmentID);
                    cmd.Parameters.AddWithValue("@Description", Description);
                    cmd.Parameters.AddWithValue("@Price", Price);
                    cmd.Parameters.AddWithValue("@Date", Date);
                    cmd.Parameters.AddWithValue("@Available", Available);
                    

                    cmd.Parameters.AddWithValue("@ServiceID", ServiceID);

                    cmd.Connection = con;
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
            }
            inpDepartmentID.ClearSelection();
            inpAvailable.Text = "";
            inpServiceName.Text = "";
            inpDescription.Text = "";
            inpPrice.Text = "";
            inpDate.Text = "";

            btnUpdate.Visible = false;
            btnCancel.Visible = false;
            btnSubmit.Visible = true;

            GridView1.EditIndex = -1;
            this.BindGrid();

        }

        protected void OnRowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            int ServiceID = Convert.ToInt32(GridView1.DataKeys[e.RowIndex].Values[0]);
            string query = "DELETE FROM Service WHERE ServiceID=@ServiceID";
            string orderDelete = "DELETE FROM Orders Where ServiceID=@ServiceID";
            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;
            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(orderDelete))
                {
                    cmd.Parameters.AddWithValue("@ServiceID", ServiceID);
                    cmd.Connection = con;
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@ServiceID", ServiceID);
                    cmd.Connection = con;
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
                
            }
            this.BindGrid();
        }
        protected void OnRowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow && e.Row.RowIndex != GridView1.EditIndex)
            {
                (e.Row.Cells[7].Controls[0] as LinkButton).Attributes["onclick"] = "return confirm('Do you want to delete this row?');";
            }
        }

        protected void OnPaging(object sender, GridViewPageEventArgs e)
        {
            GridView1.PageIndex = e.NewPageIndex;
            this.BindGrid();
        }
    }
}