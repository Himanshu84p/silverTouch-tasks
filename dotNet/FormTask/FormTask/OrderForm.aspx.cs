using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.Services.Description;

namespace FormTask
{
    public partial class OrderForm : System.Web.UI.Page
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
            string query = "SELECT * FROM Orders";
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

            int OrderID = Convert.ToInt32(GridView1.DataKeys[e.NewEditIndex].Value);
            Session["OrderID"] = OrderID;

            string ServiceID = (row.FindControl("lblServiceID") as Label).Text;

            string OrderDate = (row.FindControl("lblOrderDate") as Label).Text.ToString();
            DateTime dateTime = DateTime.Parse(OrderDate.ToString());

            string TotalAmount = (row.FindControl("lblTotalAmount") as Label).Text;

            string CustomerID = (row.FindControl("lblCustomerID") as Label).Text;
            string Pending = (row.FindControl("lblPending") as Label).Text;

            inpCustomerID.Text = CustomerID;
            inpServiceID.Text = ServiceID;
            inpOrderDate.Text = dateTime.ToString("yyyy-MM-dd");
            inpTotalAmount.Text = TotalAmount;
            inpPending.SelectedValue = Pending;


            btnUpdate.Visible = true;
            btnCancel.Visible = true;
            btnSubmit.Visible = false;

            //GridView1.EditIndex = e.NewEditIndex;
            this.BindGrid();
        }

        protected void btnSubmit_Click(object sender, EventArgs e)
        {
            
            string CustomerID = inpCustomerID.Text;
            string ServiceID = inpServiceID.Text;
            string OrderDate = inpOrderDate.Text;
            string TotalAmount = inpTotalAmount.Text;
            string Pending = inpPending.SelectedValue;

            inpCustomerID.Text = "";
            inpServiceID.Text = "";
            inpOrderDate.Text = "";
            inpTotalAmount.Text = "";
            inpPending.ClearSelection();

            string query = "INSERT INTO Orders VALUES(@CustomerID, @ServiceID, @OrderDate, @TotalAmount, @Pending)";
            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;
            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@CustomerID", CustomerID);
                    cmd.Parameters.AddWithValue("@ServiceID", ServiceID);
                    cmd.Parameters.AddWithValue("@OrderDate", OrderDate);
                    cmd.Parameters.AddWithValue("@TotalAmount", TotalAmount);
                    cmd.Parameters.AddWithValue("@Pending", Pending);
                    cmd.Connection = con;
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
            }
            this.BindGrid();
        }

        protected void btnUpdate_Click(object sender, EventArgs e)
        {
            int OrderID = Convert.ToInt32( Session["OrderID"].ToString());
            string CustomerID = inpCustomerID.Text;
            string ServiceID = inpServiceID.Text;
            string OrderDate = inpOrderDate.Text;
            string TotalAmount = inpTotalAmount.Text;
            string Pending = inpPending.SelectedValue;



            string query = "UPDATE Orders SET ServiceID=@ServiceID, CustomerID=@CustomerID, OrderDate=@OrderDate, TotalAmount=@TotalAmount, Pending=@Pending WHERE OrderID=@OrderID";

            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;

            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@CustomerID", CustomerID);
                    cmd.Parameters.AddWithValue("@ServiceID", ServiceID);
                    cmd.Parameters.AddWithValue("@OrderDate", OrderDate);
                    cmd.Parameters.AddWithValue("@TotalAmount", TotalAmount);
                    cmd.Parameters.AddWithValue("@Pending", Pending);
                    cmd.Parameters.AddWithValue("@OrderID", OrderID);



                    cmd.Connection = con;
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
            }
            inpCustomerID.Text = "";
            inpServiceID.Text = "";
            inpOrderDate.Text = "";
            inpTotalAmount.Text = "";
            inpPending.ClearSelection();

            btnUpdate.Visible = false;
            btnCancel.Visible = false;
            btnSubmit.Visible = true;

            GridView1.EditIndex = -1;
            this.BindGrid();

        }

        protected void btnCancel_Click(object sender, EventArgs e)
        {
            inpCustomerID.Text = "";
            inpServiceID.Text = "";
            inpOrderDate.Text = "";
            inpTotalAmount.Text = "";
            inpPending.ClearSelection();

            btnUpdate.Visible = false;
            btnCancel.Visible = false;

            btnSubmit.Visible = true;

            GridView1.EditIndex = -1;
            this.BindGrid();
        }

        protected void OnRowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            int OrderID = Convert.ToInt32(GridView1.DataKeys[e.RowIndex].Values[0]);
            string query = "DELETE FROM Orders WHERE OrderID=@OrderID";
            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;
            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@OrderID", OrderID);
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