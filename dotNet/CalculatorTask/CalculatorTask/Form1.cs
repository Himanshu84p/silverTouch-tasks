using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CalculatorTask
{

    public partial class Form1 : Form
    {
        private decimal firstValue = 0.0m;
        private decimal secondValue = 0.0m;
        private decimal result = 0.0m;
        private string operators = "";
        public Form1()
        {
            InitializeComponent();
            this.KeyPreview = true;
            this.KeyDown += new KeyEventHandler(Form1KeyDown);
        }

        public void clearCal()
        {
            firstValue = 0.0m;
            secondValue = 0.0m;
            result = 0.0m;
            operators = "";
        }
        private void buttonModulo_Click(object sender, EventArgs e)
        {
            if (handleEmpty())
            {
                firstValue = decimal.Parse(TextBox.Text);
                TextBox.Text = "";
                operators = "%";
            }


        }

        private void buttonCE_Click(object sender, EventArgs e)
        {
            TextBox.Text = "";
        }

        private void buttonClear_Click(object sender, EventArgs e)
        {
            clearCal();
            TextBox.Text = "0";
        }

        private void buttonEraseToLeft_Click(object sender, EventArgs e)
        {
            if (TextBox.Text.Length > 0)
            {
                string trimedString = TextBox.Text.Remove((TextBox.Text.Length) - 1);
                TextBox.Text = trimedString;
            }

        }

        private void buttonOneByX_Click(object sender, EventArgs e)
        {
            if (handleEmpty())
            {
                if (operators != "")
                {
                    calculatePrevious();
                }
                firstValue = decimal.Parse(TextBox.Text);
                TextBox.Text = "";
                operators = "onebyx";
            }

        }

        private void buttonSquare_Click(object sender, EventArgs e)
        {
            if (handleEmpty())
            {
                firstValue = decimal.Parse(TextBox.Text);
                TextBox.Text = "";
                operators = "^";
            }

        }

        private void buttonRoot_Click(object sender, EventArgs e)
        {
            if (handleEmpty())
            {
                firstValue = decimal.Parse(TextBox.Text);
                TextBox.Text = "";
                operators = "root";
            }

        }

        private void buttonDivision_Click(object sender, EventArgs e)
        {
            if (handleEmpty())
            {
                if (operators != "")
                {
                    calculatePrevious();
                }
                firstValue = decimal.Parse(TextBox.Text);
                TextBox.Text = "";
                operators = "/";
            }

        }

        private void number8_Click(object sender, EventArgs e)
        {
            if (TextBox.Text == "0")
            {
                TextBox.Text = "8";
            }
            else
            {
                TextBox.Text += "8";
            }
        }

        private void number7_Click(object sender, EventArgs e)
        {
            if (TextBox.Text == "0")
            {
                TextBox.Text = "7";
            }
            else
            {
                TextBox.Text += "7";
            }
        }

        private void number9_Click(object sender, EventArgs e)
        {
            if (TextBox.Text == "0")
            {
                TextBox.Text = "9";
            }
            else
            {
                TextBox.Text += "9";
            }
        }

        private void number4_Click(object sender, EventArgs e)
        {
            if (TextBox.Text == "0")
            {
                TextBox.Text = "4";
            }
            else
            {
                TextBox.Text += "4";
            }
        }

        private void number5_Click(object sender, EventArgs e)
        {
            if (TextBox.Text == "0")
            {
                TextBox.Text = "5";
            }
            else
            {
                TextBox.Text += "5";
            }
        }

        private void number6_Click(object sender, EventArgs e)
        {
            if (TextBox.Text == "0")
            {
                TextBox.Text = "6";
            }
            else
            {
                TextBox.Text += "6";
            }
        }

        private void number1_Click(object sender, EventArgs e)
        {
            if (TextBox.Text == "0")
            {
                TextBox.Text = "1";
            }
            else
            {
                TextBox.Text += "1";
            }

        }

        private void number2_Click(object sender, EventArgs e)
        {
            if (TextBox.Text == "0")
            {
                TextBox.Text = "2";
            }
            else
            {
                TextBox.Text += "2";
            }
        }

        private void number3_Click(object sender, EventArgs e)
        {
            if (TextBox.Text == "0")
            {
                TextBox.Text = "3";
            }
            else
            {
                TextBox.Text += "3";
            }
        }

        private void number0_Click(object sender, EventArgs e)
        {
            if (TextBox.Text == "0")
            {
                TextBox.Text = "0";
            }
            else
            {
                TextBox.Text += "0";
            }
        }

        private void buttonMultiplication_Click(object sender, EventArgs e)
        {
            if (handleEmpty())
            {
                if (operators != "")
                {
                    calculatePrevious();
                }
                firstValue = decimal.Parse(TextBox.Text);
                TextBox.Text = "";
                operators = "*";
            }

        }

        private void buttonSubtraction_Click(object sender, EventArgs e)
        {
            if (handleEmpty())
            {

                if (operators != "")
                {
                    calculatePrevious();
                }
                firstValue = decimal.Parse(TextBox.Text);
                TextBox.Text = "";
                operators = "-";
            }
        }

        private void buttonAddition_Click(object sender, EventArgs e)
        {
            if (handleEmpty())
            {
                if (operators != "")
                {
                    calculatePrevious();
                }

                firstValue = decimal.Parse(TextBox.Text);
                TextBox.Text = "";
                operators = "+";
            }

        }

        private void buttonEquals_Click(object sender, EventArgs e)
        {
            calculatePrevious();
            operators = "";
        }

        public void calculatePrevious()
        {

            switch (operators)
            {
                case "-":
                    secondValue = decimal.Parse(TextBox.Text);
                    result = firstValue - secondValue;
                    TextBox.Text = result.ToString();
                    break;
                case "+":
                    secondValue = decimal.Parse(TextBox.Text);
                    result = firstValue + secondValue;
                    TextBox.Text = result.ToString();
                    break;
                case "/":
                    secondValue = decimal.Parse(TextBox.Text);
                    if (secondValue == 0)
                    {
                        MessageBox.Show("Can not divide by 0", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        clearCal();
                    }
                    else
                    {
                        result = firstValue / secondValue;
                        TextBox.Text = result.ToString();
                    }

                    break;
                case "*":
                    secondValue = decimal.Parse(TextBox.Text);
                    result = firstValue * secondValue;
                    TextBox.Text = result.ToString();
                    break;
                case "%":
                    secondValue = decimal.Parse(TextBox.Text);
                    result = firstValue % secondValue;
                    TextBox.Text = result.ToString();
                    break;
                case "^":
                    result = firstValue * firstValue;
                    TextBox.Text = result.ToString();
                    break;
                case "root":
                    result = Convert.ToDecimal(Math.Sqrt(Convert.ToDouble(firstValue)));
                    TextBox.Text = result.ToString();
                    break;
                case "onebyx":
                    result = 1 / firstValue;
                    TextBox.Text = result.ToString();
                    break;
            }

        }

        private void buttonDot_Click(object sender, EventArgs e)
        {
            if (!TextBox.Text.Contains("."))
            {
                TextBox.Text += ".";
            }
        }

        private void buttonPlusMinus_Click(object sender, EventArgs e)
        {
            if (TextBox.Text.Contains("-"))
            {
                TextBox.Text = TextBox.Text.Trim('-');
            }
            else
            {
                TextBox.Text = "-" + TextBox.Text;
            }
        }

        public bool handleEmpty()
        {
            if (TextBox.Text == "")
            {
                MessageBox.Show("Enter number to perform", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                TextBox.Text = "";
                return false;
            }
            return true;
        }


        private void Form1KeyDown(object sender, KeyEventArgs e)
        {
            switch (e.KeyCode)
            {
                case Keys.Enter:
                    e.Handled = true;
                    buttonEquals_Click(sender, e);
                    break;
                case Keys.Back:
                    buttonClear_Click(sender, e);
                    break;
                default:
                    break;
            }
        }



    }
}
