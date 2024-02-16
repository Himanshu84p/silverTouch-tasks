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
    public partial class Form1 : System.Web.UI.Page
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

        protected void btnSubmit_Click(object sender, EventArgs e)
        {
            int Customer_ID= Convert.ToInt32(inpCustomerID.Text);
            string Customer_Name = inpCustomerName.Text;
            string Customer_Email= inpEmail.Text;
            string Customer_Phone = inpPhone.Text;
            string Customer_City = inpCity.Text;

            inpCustomerID.Text = "";
            inpCustomerName.Text = "";
            inpEmail.Text = "";
            inpPhone.Text = "";
            inpCity.Text = "";

            string query = "INSERT INTO Customer VALUES(@CustomerID, @CustomerName, @Email, @Phone, @City)";
            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;
            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@CustomerID", Customer_ID);
                    cmd.Parameters.AddWithValue("@CustomerName", Customer_Name);
                    cmd.Parameters.AddWithValue("@Email", Customer_Email);
                    cmd.Parameters.AddWithValue("@Phone", Customer_Phone);
                    cmd.Parameters.AddWithValue("@City", Customer_City);
                    cmd.Connection = con;
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
            }
            this.BindGrid();
        }

        private void BindGrid()
        {
            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;
            string query = "SELECT * FROM Customer";
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
            int CustomerID = Convert.ToInt32(GridView1.DataKeys[e.NewEditIndex].Value);
            Session["CustomerID"] = CustomerID;

            string Customer_Name = (row.FindControl("lblCustomerName") as Label).Text;

            string Customer_Email = (row.FindControl("lblEmail") as Label).Text;

            string Customer_Phone = (row.FindControl("lblPhone") as Label).Text;

            string Customer_City = (row.FindControl("lblCity") as Label).Text;

            inpCustomerID.Text = CustomerID.ToString();
            inpCustomerName.Text = Customer_Name;
            inpEmail.Text = Customer_Email;
            inpPhone.Text = Customer_Phone;
            inpCity.Text = Customer_City;

            btnUpdate.Visible = true;
            btnCancel.Visible = true;
            btnSubmit.Visible = false;

            //GridView1.EditIndex = e.NewEditIndex;
            this.BindGrid();
        }

        protected void btnUpdate_Click(object sender, EventArgs e)
        {
            string Customer_ID = Session["CustomerID"].ToString();
            string newCustomerID = inpCustomerID.Text;
            string Customer_Name = inpCustomerName.Text;
            string Customer_Email = inpEmail.Text;
            string Customer_Phone = inpPhone.Text;
            string Customer_City = inpCity.Text;

            string query = "UPDATE Customer SET CustomerID=@newCustomerID, CustomerName=@Customer_Name, Email=@Customer_Email, Phone=@Customer_Phone, City=@Customer_City WHERE CustomerID=@CustomerID";

            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;

            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@CustomerID", Customer_ID);
                    cmd.Parameters.AddWithValue("@Customer_Name", Customer_Name);
                    cmd.Parameters.AddWithValue("@Customer_Email", Customer_Email);
                    cmd.Parameters.AddWithValue("@Customer_Phone", Customer_Phone);
                    cmd.Parameters.AddWithValue("@Customer_City", Customer_City);
                    cmd.Parameters.AddWithValue("@newCustomerID", newCustomerID);

                    cmd.Connection = con;
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
            }
            inpCustomerID.Text = "";
            inpCustomerName.Text = "";
            inpEmail.Text = "";
            inpPhone.Text = "";
            inpCity.Text = "";

            btnUpdate.Visible = false;
            btnCancel.Visible = false;
            btnSubmit.Visible = true;

            GridView1.EditIndex = -1;
            this.BindGrid();

        }

        protected void btnCancel_Click(object sender, EventArgs e)
        {
            inpCustomerID.Text = "";
            inpCustomerName.Text = "";
            inpEmail.Text = "";
            inpPhone.Text = "";
            inpCity.Text = "";

            btnUpdate.Visible = false;
            btnCancel.Visible = false;

            btnSubmit.Visible = true;

            GridView1.EditIndex = -1;
            this.BindGrid();
        }


        protected void OnRowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            int CustomerID = Convert.ToInt32(GridView1.DataKeys[e.RowIndex].Values[0]);
            string query = "DELETE FROM Customer WHERE CustomerID=@CustomerID";
            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;
            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@CustomerID", CustomerID);
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