using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FormTask
{
    public partial class Form2 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                BindGrid();
                BindManagerIDDropdown();
            }
            GridView1.EditIndex = -1;
            BindGrid();
        }

        protected void btnSubmit_Click(object sender, EventArgs e)
        {
            int DepartmentID = Convert.ToInt32(inpDepartmentID.Text);
            string DepartmentName = inpDepartmentName.Text;
            string Location = inpLocation.Text;
            string ManagerID = inpManagerID.SelectedValue;
            string Budget = inpBudget.Text;

            inpDepartmentID.Text = "";
            inpDepartmentName.Text = "";
            inpLocation.Text = "";
            inpBudget.Text = "";

            string query = "INSERT INTO Department VALUES(@DepartmentID, @DepartmentName, @Location, @ManagerID, @Budget)";
            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;
            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@DepartmentID", DepartmentID);
                    cmd.Parameters.AddWithValue("@DepartmentName", DepartmentName);
                    cmd.Parameters.AddWithValue("@Location", Location);
                    cmd.Parameters.AddWithValue("@ManagerID", ManagerID);
                    cmd.Parameters.AddWithValue("@Budget", Budget);
                    cmd.Connection = con;
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
            }
            BindManagerIDDropdown();
            this.BindGrid();
        }
        private void BindGrid()
        {
            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;
            string query = "SELECT * FROM Department";
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

        protected void OnRowEditing(object sender, GridViewEditEventArgs e)
        {
            GridViewRow row = GridView1.Rows[e.NewEditIndex];

            string DepartmentID = (row.FindControl("lblDepartmentID") as Label).Text;
            Session["DepartmentID"] = DepartmentID;

            string DepartmentName = (row.FindControl("lblDepartmentName") as Label).Text;

            string Location = (row.FindControl("lblLocation") as Label).Text;

            string ManagerID = (row.FindControl("lblManagerID") as Label).Text;

            string Budget = (row.FindControl("lblBudget") as Label).Text;


            inpDepartmentID.Text = DepartmentID;
            inpDepartmentName.Text = DepartmentName;
            inpLocation.Text = Location;
            inpManagerID.SelectedValue = ManagerID;
            inpBudget.Text = Budget;

            btnUpdate.Visible = true;
            btnCancel.Visible = true;
            btnSubmit.Visible = false;

            //GridView1.EditIndex = e.NewEditIndex;
            this.BindGrid();
        }
        protected void btnCancel_Click(object sender, EventArgs e)
        {
            inpDepartmentID.Text = "";
            inpDepartmentName.Text = "";
            inpLocation.Text = "";
            inpBudget.Text = "";

            btnUpdate.Visible = false;
            btnCancel.Visible = false;
            GridView1.EditIndex = -1;
            btnSubmit.Visible = true;

            BindManagerIDDropdown();
            this.BindGrid();

        }
        protected void btnUpdate_Click(object sender, EventArgs e)
        {
            int DepartmentID = Convert.ToInt32(Session["DepartmentID"].ToString());
            string newDepartmentID = inpDepartmentID.Text;
            string DepartmentName = inpDepartmentName.Text;
            string Location = inpLocation.Text;
            string ManagerID = inpManagerID.SelectedValue;
            string Budget = inpBudget.Text;

            

            string query = "UPDATE Department SET DepartmentID=@newDepartmentID, DepartmentName=@DepartmentName, Location=@Location, ManagerID=@ManagerID, Budget=@Budget WHERE DepartmentID=@DepartmentID";

            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;

            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@DepartmentID", DepartmentID);
                    cmd.Parameters.AddWithValue("@newDepartmentID", newDepartmentID);
                    cmd.Parameters.AddWithValue("@DepartmentName", DepartmentName);
                    cmd.Parameters.AddWithValue("@Location", Location);
                    cmd.Parameters.AddWithValue("@ManagerID", ManagerID);
                    cmd.Parameters.AddWithValue("@Budget", Budget);

                    cmd.Connection = con;
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
            }
            inpDepartmentID.Text = "";
            inpDepartmentName.Text = "";
            inpLocation.Text = "";
            inpBudget.Text = "";

            btnUpdate.Visible = false;
            btnCancel.Visible = false;
            btnSubmit.Visible = true;

            GridView1.EditIndex = -1;
            BindManagerIDDropdown();
            this.BindGrid();

        }

        protected void OnRowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            int DepartmentID = Convert.ToInt32(GridView1.DataKeys[e.RowIndex].Values[0]);
            string query = "DELETE FROM department WHERE DepartmentID=@DepartmentID";
            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;
            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@DepartmentID", DepartmentID);
                    cmd.Connection = con;
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
            }
            this.BindGrid();
        }

        private void BindManagerIDDropdown()
        {
            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;
            SqlConnection con = new SqlConnection(constr);
            string com = "select -1 as DepartmentID, DepartmentName='IT', Location='Mumbai', -1 as ManagerID, Budget=500 union all Select * from Department";
            SqlDataAdapter adpt = new SqlDataAdapter(com, con);
            DataTable dt = new DataTable();
            adpt.Fill(dt);
            inpManagerID.DataSource = dt;
            inpManagerID.DataTextField = "ManagerID";
            inpManagerID.DataValueField = "ManagerID";
            inpManagerID.DataBind();
        }

        protected void OnRowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow && e.Row.RowIndex != GridView1.EditIndex)
            {
                (e.Row.Cells[6].Controls[0] as LinkButton).Attributes["onclick"] = "return confirm('Do you want to delete this row?');";
            }
        }

        protected void OnPaging(object sender, GridViewPageEventArgs e)
        {
            GridView1.PageIndex = e.NewPageIndex;
            this.BindGrid();
        }

        
    }
}