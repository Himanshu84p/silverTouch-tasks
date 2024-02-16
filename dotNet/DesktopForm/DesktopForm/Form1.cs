using DesktopForm.DataAccess;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml.Linq;
using static System.Windows.Forms.AxHost;

namespace DesktopForm
{
    public partial class Form1 : Form
    {
        public int Id = -1;
        // connectionString
        private string connectionString = @"Data Source=DESKTOP-LI40AFI\SQLEXPRESS;Initial Catalog=StudentWinForm;Integrated Security=True";
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            BindData();
            BindState();
        }

        public void BindData()
        {
            DataSet ds = sqlGet.getDataset("select * from StudentRegistration", CommandType.Text);

            studentTable.DataSource = null;
            studentTable.Rows.Clear();

            if (ds != null && ds.Tables[0] != null && ds.Tables.Count > 0)
            {
                studentTable.DataSource = ds.Tables[0];

            }
            studentTable.Columns["StudentID"].Visible = false;

        }

        public void ClearForm()
        {
            FirstName.Text = "";
            LastName.Text = "";
            Email.Text = "";
            PhoneNumber.Text = "";
            Male.Checked = false;
            Female.Checked = false;
            Other.Checked = false;
            College.Text = "";
            DateOfBirth.Text = "";
            Sports.Checked = false;
            Reading.Checked = false;
            Tech.Checked = false;
            Gaming.Checked = false;
            Traveling.Checked = false;
            City.Text = "";
            BindState();
        }

        public bool ValidateForm(string Gender)
        {
            if(FirstName.Text == "" || LastName.Text == "" || Email.Text == "" || PhoneNumber.Text == "" || College.Text == "" ||   State.Text == "" || DateOfBirth.Text =="" || Gender == "")
            {
                MessageBox.Show("Please fill all the Data to submit","Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return false;
            } 
            if(!IsValidEmail(Email.Text)) {
                MessageBox.Show("Enter Valid Email", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error); 
                return false; }
            if (!IsValidPhoneNumber(PhoneNumber.Text)) {
                MessageBox.Show("Enter Valid Mobile", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return false; }

            return true;
        }
        private bool IsValidEmail(string email)
        {
            try
            {
                var addr = new System.Net.Mail.MailAddress(email)
;
                return addr.Address == email;
            }
            catch
            {
                return false;
            }
        }

        private bool IsValidPhoneNumber(string phoneNumber)
        {
            return System.Text.RegularExpressions.Regex.IsMatch(phoneNumber, @"^[0-9\- ]+$");
        }

        private void SubmitBtn_Click(object sender, EventArgs e)
        {   
            string firstName = FirstName.Text;
            string lastName = LastName.Text;
            string email = Email.Text;
            string phoneNumber = PhoneNumber.Text;
            string gender = GetSelectedGender(); // Retrieve selected item from Radiobtn
            string college = College.Text;
            string state = State.Text; // Retrieve selected item from ComboBox state
            string city = City.Text;
            DateTime dateOfBirth = DateOfBirth.Value;
            string hobbies = GetSelectedHobbies();

            // Save data to the database
            try
            {
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    connection.Open();
                    string query = "";
                    if(Id > 0)
                    {
                        query = "Update StudentRegistration set FirstName=@FirstName, LastName=@LastName, Email=@Email, PhoneNumber=@PhoneNumber, Gender=@Gender, College=@College, State=@State, City=@City, DateOfBirth=@DateOfBirth, Hobbies=@Hobbies where StudentID=@Id";
                    } else
                    {
                        query = "INSERT INTO StudentRegistration VALUES (@FirstName, @LastName, @Email, @PhoneNumber, @Gender, @College, @State, @City, @DateOfBirth, @Hobbies)";
                    } 
                    SqlCommand command = new SqlCommand(query, connection);
                    command.Parameters.AddWithValue("@FirstName", firstName);
                    command.Parameters.AddWithValue("@LastName", lastName);
                    command.Parameters.AddWithValue("@Email", email);
                    command.Parameters.AddWithValue("@PhoneNumber", phoneNumber);
                    command.Parameters.AddWithValue("@Gender", gender);
                    command.Parameters.AddWithValue("@College", college);
                    command.Parameters.AddWithValue("@State", state);
                    command.Parameters.AddWithValue("@City", city);
                    command.Parameters.AddWithValue("@DateOfBirth", dateOfBirth);
                    command.Parameters.AddWithValue("@Hobbies", hobbies);
                    command.Parameters.AddWithValue("@Id", Id);
                    if (ValidateForm(gender))
                    {
                        command.ExecuteNonQuery();
                        MessageBox.Show("Data saved successfully!");
                    } else
                    {
                        return;
                    }                
                    
                }
                BindData();
                ClearForm();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error occured: " + ex.Message);
            }
            Id = -1;

        }

        private void BindState()
        {
            string constr = ConfigurationManager.ConnectionStrings["DesktopForm.Properties.Settings.StudentWinFormConnectionString"].ConnectionString;
            try
            {
                string query = "select  -1 as StateID, '--Select--' as State  union all Select * from State";
                using (SqlConnection con = new SqlConnection(constr))
                {
                    con.Open();
                    SqlCommand command = new SqlCommand(query, con);
                    SqlDataReader reader = command.ExecuteReader();

                    DataTable dt = new DataTable();
                    dt.Load(reader);

                    // Bind data to ComboBox
                    State.DataSource = dt;
                    State.DisplayMember = "State"; // Display the State
                    State.ValueMember = "State";
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message);
            }
        }
        private string GetSelectedHobbies()
        {
            string hobbies = "";
            if (Sports.Checked)
                hobbies += "Sports,";
            if (Reading.Checked)
                hobbies += "Reading,";
            if (Gaming.Checked)
                hobbies += "Gaming,";
            if (Traveling.Checked)
                hobbies += "Traveling,";
            if (Tech.Checked)
                hobbies += "Tech";
            return hobbies.TrimEnd(',');
        }

        private string GetSelectedGender()
        {
            if (Male.Checked)
            {
                return "Male";
            }
            else if (Female.Checked)
            {
                return "Female";
            }
            else if (Other.Checked)
            {
                return "Other";
            }
            else
            {
                return null; // No gender selected
            }
        }

        private void State_SelectedIndexChanged(object sender, EventArgs e)
        {
            BindCityDropdown(State.SelectedIndex);
        }

        private void BindCityDropdown(int StateID)
        {
            string constr = ConfigurationManager.ConnectionStrings["DesktopForm.Properties.Settings.StudentWinFormConnectionString"].ConnectionString;
            SqlConnection con = new SqlConnection(constr);
            string com = "Select * from City WHERE StateID = " + Convert.ToInt32(StateID);
            SqlDataAdapter adpt = new SqlDataAdapter(com, con);
            DataTable dt = new DataTable();
            adpt.Fill(dt);


            City.DisplayMember = "CityName";
            City.ValueMember = "CityID";
            City.DataSource = dt;
        }

        private void DeleteBtn_Click(object sender, EventArgs e)
        {
            if (Id > 0)
            {
                sqlGet.executeNonQuery("delete from StudentRegistration where StudentID=" + Id.ToString(), CommandType.Text);
                BindData();
                MessageBox.Show("Row Deleted Successfully","Success",MessageBoxButtons.OK,MessageBoxIcon.Information);
                ClearForm();
                Id = -1;
            }
            else
            {
                MessageBox.Show("Select Atleast one recod to Perform Delete operation!!");
                return;
            }


        }

        private void StudentTableCellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex == -1)
            {
                return;
            }
            Id = Convert.ToInt32(studentTable.Rows[e.RowIndex].Cells["StudentID"].Value);
        }

        private void ResetBtn_Click(object sender, EventArgs e)
        {
            ClearForm();
            Id = -1;
        }

        private void EditBtn_Click(object sender, EventArgs e)
        {
            ClearForm();
            if (Id > 0)
            {
                DataSet ds = sqlGet.getDataset("select FirstName, LastName, Email, PhoneNumber, Gender, College, State, City, DateOfBirth, Hobbies from StudentRegistration where StudentID=" + Id.ToString(), CommandType.Text);
                if (ds != null && ds.Tables[0] != null && ds.Tables.Count > 0)
                {
                    FirstName.Text = Convert.ToString(ds.Tables[0].Rows[0]["FirstName"]);
                    LastName.Text = Convert.ToString(ds.Tables[0].Rows[0]["LastName"]);
                    Email.Text = Convert.ToString(ds.Tables[0].Rows[0]["Email"]);
                    PhoneNumber.Text = Convert.ToString(ds.Tables[0].Rows[0]["PhoneNumber"]);
                    SelectEditGender(Convert.ToString(ds.Tables[0].Rows[0]["Gender"]));
                    College.Text = Convert.ToString(ds.Tables[0].Rows[0]["College"]);
                    DateOfBirth.Text = Convert.ToString(ds.Tables[0].Rows[0]["DateOfBirth"]);
                    State.Text = Convert.ToString(ds.Tables[0].Rows[0]["State"]);
                    City.Text = Convert.ToString(ds.Tables[0].Rows[0]["City"]);
                    SelectEditHobbies(Convert.ToString(ds.Tables[0].Rows[0]["Hobbies"]));
                }
            }
        }
        public void SelectEditGender(string Gender)
        {
            if (Gender == "Male")
            {
                Male.Checked = true;
                return;
            }
            else if (Gender == "Female")
            {
                Female.Checked = true;
                return;
            }
            else if (Gender == "Other")
            {
                Other.Checked = true;
                return;
            }
            else return;
        }

        public void SelectEditHobbies(string Hobby)
        {   
            string[] hobbyArray = Hobby.Split(',');

            foreach (string s in hobbyArray)
            {
                if (s == "Sports")
                {
                    Sports.Checked = true;
                }
                if (s == "Reading")
                {
                    Reading.Checked = true;
                }
                if (s == "Gaming")
                {
                    Gaming.Checked = true;
                }
                if (s == "Traveling")
                {
                    Traveling.Checked = true;
                }
                if (s == "Tech")
                {
                    Tech.Checked = true;
                } 
            }

        }
    }

}
