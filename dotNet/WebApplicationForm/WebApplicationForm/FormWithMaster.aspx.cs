using System;
using System.Data;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplicationForm
{
    public partial class FormWithMaster : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            DataTable data = new DataTable();
            data.Columns.Add("First Name");
            data.Columns.Add("Last Name");
            data.Columns.Add("Email");
            data.Rows.Add("Himanshu", "Prajapati", "himanshu123@gamil.com");
            data.Rows.Add("Kevin" , "Patel", "kevin123@gmail.com");
            data.Rows.Add("Divyesh", "Mavadiya" , "divyesh123@gamil.com");

            dataList.DataSource = data;
            dataList.DataBind();

            dataGrid.DataSource = data;
            dataGrid.DataBind();
        }

        protected void submit_Btn(object sender, EventArgs e)
        {

            surnameText.InnerText = inpSurname.Text;
            emailText.InnerText = inpEmail.Text;

            inpName.Text = "";
        }

        protected void inpName_TextChanged(object sender, EventArgs e)
        {
            nameText.InnerText = inpName.Text;
        }

        protected void LinkButton_Click(object sender, EventArgs e)
        {
            title.Text = "You Clicked LinkButton";
        }

        protected void LinkButton_Command(object sender, CommandEventArgs e)
        {
            title.Text = " You Clikced " + e.CommandName + " Command " + e.CommandArgument;
        }

        protected void imageButton_Click(object sender, ImageClickEventArgs e)
        {
            title.Text = "You clicked the Image Button control at the cordinates : " + e.X.ToString() + " , " + e.Y.ToString();
        }

        protected void cityList_SelectedIndexChanged(object sender, EventArgs e)
        {
            title.Text = cityList.SelectedValue;
        }

        protected void radioSubmit_Click(object sender, EventArgs e)
        {
            if (radioButton1.Checked)
            {
                title.Text = "Selected gender is " + radioButton1.Text;
            }
            else
            {
                title.Text = "Selected gender is " + radioButton2.Text;
            }
        }

        protected void Calendar_SelectionChanged(object sender, EventArgs e)
        {
            title.Text = "Selected date is : " + Calendar.SelectedDate.ToString("M");
        }

        protected void chechkBox_Click(object sender, EventArgs e)
        {
            title.Text = "";
            if (checkbox1.Checked)
            {
                title.Text += checkbox1.Text + " ";
            }
            if (checkbox2.Checked)
            {
                title.Text += checkbox2.Text + " ";
            }
            if (checkbox3.Checked) { title.Text += checkbox3.Text + " "; }
        }
    }
}