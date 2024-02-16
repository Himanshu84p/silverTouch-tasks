namespace DesktopForm
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.label10 = new System.Windows.Forms.Label();
            this.label11 = new System.Windows.Forms.Label();
            this.FirstName = new System.Windows.Forms.TextBox();
            this.Email = new System.Windows.Forms.TextBox();
            this.LastName = new System.Windows.Forms.TextBox();
            this.PhoneNumber = new System.Windows.Forms.TextBox();
            this.Male = new System.Windows.Forms.RadioButton();
            this.Female = new System.Windows.Forms.RadioButton();
            this.State = new System.Windows.Forms.ComboBox();
            this.City = new System.Windows.Forms.ComboBox();
            this.DateOfBirth = new System.Windows.Forms.DateTimePicker();
            this.Sports = new System.Windows.Forms.CheckBox();
            this.Reading = new System.Windows.Forms.CheckBox();
            this.Gaming = new System.Windows.Forms.CheckBox();
            this.Traveling = new System.Windows.Forms.CheckBox();
            this.Tech = new System.Windows.Forms.CheckBox();
            this.SubmitBtn = new System.Windows.Forms.Button();
            this.EditBtn = new System.Windows.Forms.Button();
            this.ResetBtn = new System.Windows.Forms.Button();
            this.button4 = new System.Windows.Forms.Button();
            this.College = new System.Windows.Forms.TextBox();
            this.Other = new System.Windows.Forms.RadioButton();
            this.studentTable = new System.Windows.Forms.DataGridView();
            this.studentRegistrationBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.studentWinFormDataSet = new DesktopForm.StudentWinFormDataSet();
            this.studentRegistrationTableAdapter = new DesktopForm.StudentWinFormDataSetTableAdapters.StudentRegistrationTableAdapter();
            ((System.ComponentModel.ISupportInitialize)(this.studentTable)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.studentRegistrationBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.studentWinFormDataSet)).BeginInit();
            this.SuspendLayout();
            // 
            // label2
            // 
            this.label2.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(447, 9);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(230, 31);
            this.label2.TabIndex = 2;
            this.label2.Text = "Registration Form";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(64, 99);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(94, 20);
            this.label1.TabIndex = 3;
            this.label1.Text = "First Name :";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(64, 139);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(94, 20);
            this.label3.TabIndex = 4;
            this.label3.Text = "Last Name :";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(115, 358);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(43, 20);
            this.label4.TabIndex = 5;
            this.label4.Text = "City :";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label5.Location = new System.Drawing.Point(48, 395);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(110, 20);
            this.label5.TabIndex = 6;
            this.label5.Text = "Date Of Birth :";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(87, 278);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(70, 20);
            this.label6.TabIndex = 7;
            this.label6.Text = "College :";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label7.Location = new System.Drawing.Point(87, 244);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(71, 20);
            this.label7.TabIndex = 8;
            this.label7.Text = "Gender :";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label8.Location = new System.Drawing.Point(39, 215);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(119, 20);
            this.label8.TabIndex = 9;
            this.label8.Text = "Phone Number:";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label9.Location = new System.Drawing.Point(102, 177);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(56, 20);
            this.label9.TabIndex = 10;
            this.label9.Text = "Email :";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label10.Location = new System.Drawing.Point(101, 317);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(56, 20);
            this.label10.TabIndex = 11;
            this.label10.Text = "State :";
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label11.Location = new System.Drawing.Point(82, 429);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(76, 20);
            this.label11.TabIndex = 12;
            this.label11.Text = "Hobbies :";
            // 
            // FirstName
            // 
            this.FirstName.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.FirstName.Location = new System.Drawing.Point(173, 87);
            this.FirstName.Multiline = true;
            this.FirstName.Name = "FirstName";
            this.FirstName.Size = new System.Drawing.Size(285, 32);
            this.FirstName.TabIndex = 13;
            // 
            // Email
            // 
            this.Email.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Email.Location = new System.Drawing.Point(173, 165);
            this.Email.Multiline = true;
            this.Email.Name = "Email";
            this.Email.Size = new System.Drawing.Size(285, 32);
            this.Email.TabIndex = 14;
            // 
            // LastName
            // 
            this.LastName.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.LastName.Location = new System.Drawing.Point(173, 127);
            this.LastName.Multiline = true;
            this.LastName.Name = "LastName";
            this.LastName.Size = new System.Drawing.Size(285, 32);
            this.LastName.TabIndex = 15;
            // 
            // PhoneNumber
            // 
            this.PhoneNumber.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.PhoneNumber.Location = new System.Drawing.Point(173, 203);
            this.PhoneNumber.Multiline = true;
            this.PhoneNumber.Name = "PhoneNumber";
            this.PhoneNumber.Size = new System.Drawing.Size(285, 32);
            this.PhoneNumber.TabIndex = 16;
            // 
            // Male
            // 
            this.Male.AutoSize = true;
            this.Male.Location = new System.Drawing.Point(173, 246);
            this.Male.Name = "Male";
            this.Male.Size = new System.Drawing.Size(48, 17);
            this.Male.TabIndex = 17;
            this.Male.TabStop = true;
            this.Male.Text = "Male";
            this.Male.UseVisualStyleBackColor = true;
            // 
            // Female
            // 
            this.Female.AutoSize = true;
            this.Female.Location = new System.Drawing.Point(227, 246);
            this.Female.Name = "Female";
            this.Female.Size = new System.Drawing.Size(59, 17);
            this.Female.TabIndex = 18;
            this.Female.TabStop = true;
            this.Female.Text = "Female";
            this.Female.UseVisualStyleBackColor = true;
            // 
            // State
            // 
            this.State.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F);
            this.State.FormattingEnabled = true;
            this.State.Items.AddRange(new object[] {
            "Gujarat"});
            this.State.Location = new System.Drawing.Point(173, 309);
            this.State.Name = "State";
            this.State.Size = new System.Drawing.Size(285, 28);
            this.State.TabIndex = 20;
            this.State.SelectedIndexChanged += new System.EventHandler(this.State_SelectedIndexChanged);
            // 
            // City
            // 
            this.City.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F);
            this.City.FormattingEnabled = true;
            this.City.Items.AddRange(new object[] {
            "Mehsana"});
            this.City.Location = new System.Drawing.Point(173, 350);
            this.City.Name = "City";
            this.City.Size = new System.Drawing.Size(285, 28);
            this.City.TabIndex = 21;
            // 
            // DateOfBirth
            // 
            this.DateOfBirth.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F);
            this.DateOfBirth.Location = new System.Drawing.Point(173, 389);
            this.DateOfBirth.Name = "DateOfBirth";
            this.DateOfBirth.Size = new System.Drawing.Size(285, 26);
            this.DateOfBirth.TabIndex = 22;
            // 
            // Sports
            // 
            this.Sports.AutoSize = true;
            this.Sports.Location = new System.Drawing.Point(173, 431);
            this.Sports.Name = "Sports";
            this.Sports.Size = new System.Drawing.Size(56, 17);
            this.Sports.TabIndex = 23;
            this.Sports.Text = "Sports";
            this.Sports.UseVisualStyleBackColor = true;
            // 
            // Reading
            // 
            this.Reading.AutoSize = true;
            this.Reading.Location = new System.Drawing.Point(259, 431);
            this.Reading.Name = "Reading";
            this.Reading.Size = new System.Drawing.Size(66, 17);
            this.Reading.TabIndex = 24;
            this.Reading.Text = "Reading";
            this.Reading.UseVisualStyleBackColor = true;
            // 
            // Gaming
            // 
            this.Gaming.AutoSize = true;
            this.Gaming.Location = new System.Drawing.Point(173, 454);
            this.Gaming.Name = "Gaming";
            this.Gaming.Size = new System.Drawing.Size(62, 17);
            this.Gaming.TabIndex = 25;
            this.Gaming.Text = "Gaming";
            this.Gaming.UseVisualStyleBackColor = true;
            // 
            // Traveling
            // 
            this.Traveling.AutoSize = true;
            this.Traveling.Location = new System.Drawing.Point(259, 454);
            this.Traveling.Name = "Traveling";
            this.Traveling.Size = new System.Drawing.Size(70, 17);
            this.Traveling.TabIndex = 26;
            this.Traveling.Text = "Traveling";
            this.Traveling.UseVisualStyleBackColor = true;
            // 
            // Tech
            // 
            this.Tech.AutoSize = true;
            this.Tech.Location = new System.Drawing.Point(173, 477);
            this.Tech.Name = "Tech";
            this.Tech.Size = new System.Drawing.Size(51, 17);
            this.Tech.TabIndex = 27;
            this.Tech.Text = "Tech";
            this.Tech.UseVisualStyleBackColor = true;
            // 
            // SubmitBtn
            // 
            this.SubmitBtn.BackColor = System.Drawing.Color.LightSkyBlue;
            this.SubmitBtn.FlatAppearance.BorderColor = System.Drawing.Color.White;
            this.SubmitBtn.Font = new System.Drawing.Font("Calibri", 10F, System.Drawing.FontStyle.Bold);
            this.SubmitBtn.ForeColor = System.Drawing.Color.Black;
            this.SubmitBtn.Location = new System.Drawing.Point(173, 519);
            this.SubmitBtn.Name = "SubmitBtn";
            this.SubmitBtn.Size = new System.Drawing.Size(73, 27);
            this.SubmitBtn.TabIndex = 28;
            this.SubmitBtn.Text = "Submit";
            this.SubmitBtn.UseVisualStyleBackColor = false;
            this.SubmitBtn.Click += new System.EventHandler(this.SubmitBtn_Click);
            // 
            // EditBtn
            // 
            this.EditBtn.BackColor = System.Drawing.Color.LightSkyBlue;
            this.EditBtn.FlatAppearance.BorderColor = System.Drawing.Color.White;
            this.EditBtn.Font = new System.Drawing.Font("Calibri", 10F, System.Drawing.FontStyle.Bold);
            this.EditBtn.ForeColor = System.Drawing.Color.Black;
            this.EditBtn.Location = new System.Drawing.Point(252, 519);
            this.EditBtn.Name = "EditBtn";
            this.EditBtn.Size = new System.Drawing.Size(73, 27);
            this.EditBtn.TabIndex = 29;
            this.EditBtn.Text = "Edit";
            this.EditBtn.UseVisualStyleBackColor = false;
            this.EditBtn.Click += new System.EventHandler(this.EditBtn_Click);
            // 
            // ResetBtn
            // 
            this.ResetBtn.BackColor = System.Drawing.Color.LightSkyBlue;
            this.ResetBtn.FlatAppearance.BorderColor = System.Drawing.Color.White;
            this.ResetBtn.Font = new System.Drawing.Font("Calibri", 10F, System.Drawing.FontStyle.Bold);
            this.ResetBtn.ForeColor = System.Drawing.Color.Black;
            this.ResetBtn.Location = new System.Drawing.Point(173, 552);
            this.ResetBtn.Name = "ResetBtn";
            this.ResetBtn.Size = new System.Drawing.Size(73, 27);
            this.ResetBtn.TabIndex = 30;
            this.ResetBtn.Text = "Reset";
            this.ResetBtn.UseVisualStyleBackColor = false;
            this.ResetBtn.Click += new System.EventHandler(this.ResetBtn_Click);
            // 
            // button4
            // 
            this.button4.BackColor = System.Drawing.Color.LightSkyBlue;
            this.button4.FlatAppearance.BorderColor = System.Drawing.Color.White;
            this.button4.Font = new System.Drawing.Font("Calibri", 10F, System.Drawing.FontStyle.Bold);
            this.button4.ForeColor = System.Drawing.Color.Black;
            this.button4.Location = new System.Drawing.Point(331, 519);
            this.button4.Name = "button4";
            this.button4.Size = new System.Drawing.Size(73, 27);
            this.button4.TabIndex = 31;
            this.button4.Text = "Delete";
            this.button4.UseVisualStyleBackColor = false;
            this.button4.Click += new System.EventHandler(this.DeleteBtn_Click);
            // 
            // College
            // 
            this.College.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.College.Location = new System.Drawing.Point(173, 269);
            this.College.Multiline = true;
            this.College.Name = "College";
            this.College.Size = new System.Drawing.Size(285, 32);
            this.College.TabIndex = 32;
            // 
            // Other
            // 
            this.Other.AutoSize = true;
            this.Other.Location = new System.Drawing.Point(292, 246);
            this.Other.Name = "Other";
            this.Other.Size = new System.Drawing.Size(51, 17);
            this.Other.TabIndex = 33;
            this.Other.TabStop = true;
            this.Other.Text = "Other";
            this.Other.UseVisualStyleBackColor = true;
            // 
            // studentTable
            // 
            this.studentTable.AllowUserToAddRows = false;
            this.studentTable.AllowUserToDeleteRows = false;
            this.studentTable.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.studentTable.Location = new System.Drawing.Point(43, 585);
            this.studentTable.Name = "studentTable";
            this.studentTable.ReadOnly = true;
            this.studentTable.Size = new System.Drawing.Size(1143, 282);
            this.studentTable.TabIndex = 34;
            this.studentTable.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.StudentTableCellClick);
            // 
            // studentRegistrationBindingSource
            // 
            this.studentRegistrationBindingSource.DataMember = "StudentRegistration";
            this.studentRegistrationBindingSource.DataSource = this.studentWinFormDataSet;
            // 
            // studentWinFormDataSet
            // 
            this.studentWinFormDataSet.DataSetName = "StudentWinFormDataSet";
            this.studentWinFormDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // studentRegistrationTableAdapter
            // 
            this.studentRegistrationTableAdapter.ClearBeforeFill = true;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1300, 1061);
            this.Controls.Add(this.Other);
            this.Controls.Add(this.studentTable);
            this.Controls.Add(this.College);
            this.Controls.Add(this.button4);
            this.Controls.Add(this.ResetBtn);
            this.Controls.Add(this.EditBtn);
            this.Controls.Add(this.SubmitBtn);
            this.Controls.Add(this.Tech);
            this.Controls.Add(this.Traveling);
            this.Controls.Add(this.Gaming);
            this.Controls.Add(this.Reading);
            this.Controls.Add(this.Sports);
            this.Controls.Add(this.DateOfBirth);
            this.Controls.Add(this.City);
            this.Controls.Add(this.State);
            this.Controls.Add(this.Female);
            this.Controls.Add(this.Male);
            this.Controls.Add(this.PhoneNumber);
            this.Controls.Add(this.LastName);
            this.Controls.Add(this.Email);
            this.Controls.Add(this.FirstName);
            this.Controls.Add(this.label11);
            this.Controls.Add(this.label10);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.label2);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.studentTable)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.studentRegistrationBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.studentWinFormDataSet)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.TextBox FirstName;
        private System.Windows.Forms.TextBox Email;
        private System.Windows.Forms.TextBox LastName;
        private System.Windows.Forms.TextBox PhoneNumber;
        private System.Windows.Forms.RadioButton Male;
        private System.Windows.Forms.RadioButton Female;
        private System.Windows.Forms.ComboBox State;
        private System.Windows.Forms.ComboBox City;
        private System.Windows.Forms.DateTimePicker DateOfBirth;
        private System.Windows.Forms.CheckBox Sports;
        private System.Windows.Forms.CheckBox Reading;
        private System.Windows.Forms.CheckBox Gaming;
        private System.Windows.Forms.CheckBox Traveling;
        private System.Windows.Forms.CheckBox Tech;
        private System.Windows.Forms.Button SubmitBtn;
        private System.Windows.Forms.Button EditBtn;
        private System.Windows.Forms.Button ResetBtn;
        private System.Windows.Forms.Button button4;
        private System.Windows.Forms.TextBox College;
        private System.Windows.Forms.RadioButton Other;
        private System.Windows.Forms.DataGridView studentTable;
        private StudentWinFormDataSet studentWinFormDataSet;
        private System.Windows.Forms.BindingSource studentRegistrationBindingSource;
        private StudentWinFormDataSetTableAdapters.StudentRegistrationTableAdapter studentRegistrationTableAdapter;
    }
}

