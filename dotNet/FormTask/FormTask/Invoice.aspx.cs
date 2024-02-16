using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace FormTask
{
    public partial class Invoice : System.Web.UI.Page
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
            string query = "SELECT * FROM Invoice";
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
            int InvoiceID = Convert.ToInt32(GridView1.DataKeys[e.NewEditIndex].Value);
            Session["InvoiceID"] = InvoiceID;

            string OrderID = (row.FindControl("lblOrderID") as Label).Text;

            string AmountPaid = (row.FindControl("lblAmountPaid") as Label).Text;

            string PaymentDate = (row.FindControl("lblPaymentDate") as Label).Text;
            DateTime dateTime = DateTime.Parse(PaymentDate.ToString());

            string PaymentMethod = (row.FindControl("lblPaymentMethod") as Label).Text;

            inpOrderID.Text = OrderID.ToString();
            inpAmountPaid.Text = AmountPaid.ToString();
            inpPaymentDate.Text = dateTime.ToString("yyyy-MM-dd");
            inpPaymentMethod.Text = PaymentMethod;

            btnUpdate.Visible = true;
            btnCancel.Visible = true;
            btnSubmit.Visible = false;

            //GridView1.EditIndex = e.NewEditIndex;
            this.BindGrid();
        }

        protected void btnSubmit_Click(object sender, EventArgs e)
        {

            int OrderID = Convert.ToInt32( inpOrderID.Text);
            int AmountPaid = Convert.ToInt32(inpAmountPaid.Text);
            string PaymentDate = inpPaymentDate.Text;
            string PaymentMethod = inpPaymentMethod.Text;

            

            string query = "INSERT INTO Invoice VALUES(@OrderID, @AmountPaid, @PaymentDate, @PaymentMethod)";
            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;
            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@OrderID", OrderID);
                    cmd.Parameters.AddWithValue("@AmountPaid", AmountPaid);
                    cmd.Parameters.AddWithValue("@PaymentDate", PaymentDate);
                    cmd.Parameters.AddWithValue("@PaymentMethod", PaymentMethod);
                    cmd.Connection = con;
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
            }
            inpOrderID.Text = "";
            inpAmountPaid.Text = "";
            inpPaymentDate.Text = "";
            inpPaymentMethod.Text = "";
            this.BindGrid();
        }

        protected void btnUpdate_Click(object sender, EventArgs e)
        {
            int InvoiceID = Convert.ToInt32(Session["InvoiceID"].ToString());
            int OrderID = Convert.ToInt32(inpOrderID.Text);
            string AmountPaid = inpAmountPaid.Text;
            string PaymentDate = inpPaymentDate.Text;
            string PaymentMethod = inpPaymentMethod.Text;



            string query = "UPDATE Invoice SET OrderID=@OrderID, AmountPaid=@AmountPaid, PaymentDate=@PaymentDate, PaymentMethod=@PaymentMethod WHERE InvoiceID=@InvoiceID";

            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;

            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@OrderID", OrderID);
                    cmd.Parameters.AddWithValue("@AmountPaid", AmountPaid);
                    cmd.Parameters.AddWithValue("@PaymentDate", PaymentDate);
                    cmd.Parameters.AddWithValue("@PaymentMethod", PaymentMethod);
                    cmd.Parameters.AddWithValue("@InvoiceID", InvoiceID);
                    cmd.Connection = con;
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
            }
            inpOrderID.Text = "";
            inpAmountPaid.Text = "";
            inpPaymentDate.Text = "";
            inpPaymentMethod.Text = "";

            btnUpdate.Visible = false;
            btnCancel.Visible = false;
            btnSubmit.Visible = true;

            GridView1.EditIndex = -1;
            this.BindGrid();

        }

        protected void btnCancel_Click(object sender, EventArgs e)
        {
            inpOrderID.Text = "";
            inpAmountPaid.Text = "";
            inpPaymentDate.Text = "";
            inpPaymentMethod.Text = "";

            btnUpdate.Visible = false;
            btnCancel.Visible = false;

            btnSubmit.Visible = true;

            GridView1.EditIndex = -1;
            this.BindGrid();
        }

        protected void OnRowUpdating(object sender, GridViewUpdateEventArgs e)
        {
            GridViewRow row = GridView1.Rows[e.RowIndex];
            int InvoiceID = Convert.ToInt32(GridView1.DataKeys[e.RowIndex].Values[0]);

            int OrderID = Convert.ToInt32( (row.FindControl("txtOrderID") as TextBox).Text);

            int AmountPaid = Convert.ToInt32( (row.FindControl("txtAmountPaid") as TextBox).Text);

            string PaymentDate = (row.FindControl("txtPaymentDate") as TextBox).Text;

            string PaymentMethod = (row.FindControl("txtPaymentMethod") as TextBox).Text;

            string query = "UPDATE Invoice SET OrderID=@OrderID, AmountPaid=@AmountPaid, PaymentDate=@PaymentDate, PaymentMethod=@PaymentMethod WHERE InvoiceID=@InvoiceID";

            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;

            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@OrderID", OrderID);
                    cmd.Parameters.AddWithValue("@AmountPaid", AmountPaid);
                    cmd.Parameters.AddWithValue("@PaymentDate", PaymentDate);
                    cmd.Parameters.AddWithValue("@PaymentMethod", PaymentMethod);
                    cmd.Parameters.AddWithValue("@InvoiceID", InvoiceID);

                    cmd.Connection = con;
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
            }
            GridView1.EditIndex = -1;
            this.BindGrid();
        }

        protected void OnRowCancelingEdit(object sender, EventArgs e)
        {
            GridView1.EditIndex = -1;
            this.BindGrid();
        }

        protected void OnRowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            int InvoiceID = Convert.ToInt32(GridView1.DataKeys[e.RowIndex].Values[0]);
            string query = "DELETE FROM Invoice WHERE InvoiceID=@InvoiceID";
            string constr = ConfigurationManager.ConnectionStrings["ServiceConnectionString"].ConnectionString;
            using (SqlConnection con = new SqlConnection(constr))
            {
                using (SqlCommand cmd = new SqlCommand(query))
                {
                    cmd.Parameters.AddWithValue("@InvoiceID", InvoiceID);
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
                (e.Row.Cells[5].Controls[0] as LinkButton).Attributes["onclick"] = "return confirm('Do you want to delete this row?');";
            }
        }

        protected void OnPaging(object sender, GridViewPageEventArgs e)
        {
            GridView1.PageIndex = e.NewPageIndex;
            this.BindGrid();
        }
    }
}