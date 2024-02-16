<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="FormWithMaster.aspx.cs" Inherits="WebApplicationForm.FormWithMaster" %>


<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <link href="Css/styles.css" rel="stylesheet" />
    <style>
        /*.server-controls {
            display: flex;
            flex-direction: column;
            row-gap: 1rem;
        }*/

        .title {
            font-size: 1.5rem;
        }

        .anchor {
            text-decoration: none;
        }
    </style>
    <h1>Sign Up Form</h1>

    <asp:Panel CssClass="formContainer" runat="server">

        <div class="row p-2">
            <div class="col-md-4">
                <asp:Label ID="Label1" runat="server" Text="Name"></asp:Label>
                <asp:TextBox ID="inpName" runat="server" OnTextChanged="inpName_TextChanged" AutoPostBack="true" CssClass="input-group"></asp:TextBox>

            </div>
            <div class="col-md-4">
                <asp:Label ID="Label2" runat="server" Text="Surname"></asp:Label>
                <asp:TextBox ID="inpSurname" runat="server" CssClass="input-group"></asp:TextBox>

            </div>
            <div class="col-md-4">
                <asp:Label ID="Label3" runat="server" Text="Email"></asp:Label>
                <asp:TextBox ID="inpEmail" runat="server" CssClass="input-group"></asp:TextBox>

            </div>

        </div>
        <div class="p-3 server-controls">
            <asp:Button class="col-md-3 btn btn-primary" runat="server" Text="Submit" OnClick="submit_Btn" ID="subBtn" OnClientClick="submit_client_click()" />

            <asp:LinkButton CssClass="anchor" ID="LinkButton" runat="server" OnCommand="LinkButton_Command" CommandArgument="100" CommandName="Book Ticket">Link Button1</asp:LinkButton>

            <asp:LinkButton CssClass="anchor" ID="LinkButton2" runat="server" OnCommand="LinkButton_Command" CommandArgument="200" CommandName="Book Ticket">Link Button2</asp:LinkButton>
            <asp:ImageButton ID="imageButton" runat="server" AlternateText="image of button" ImageUrl="~/Images2/pic.jpg" OnClick="imageButton_Click" />
            <asp:DropDownList ID="cityList" AutoPostBack="true" runat="server" OnSelectedIndexChanged="cityList_SelectedIndexChanged">
                <asp:ListItem>Mehsana</asp:ListItem>
                <asp:ListItem>Surat</asp:ListItem>
                <asp:ListItem>Gandhinagar</asp:ListItem>
            </asp:DropDownList>

            <asp:ListBox runat="server" ID="listBox" SelectionMode="Multiple">
                <asp:ListItem>Item 1</asp:ListItem>
                <asp:ListItem>Item 2</asp:ListItem>
                <asp:ListItem>Item 3</asp:ListItem>
                <asp:ListItem>Item 4</asp:ListItem>
                <asp:ListItem>Item 5</asp:ListItem>
                <asp:ListItem>Item 6</asp:ListItem>

            </asp:ListBox>

            <asp:HyperLink AccessKey="a" runat="server" Text="Java T Point" NavigateUrl="https://www.javatpoint.com/asp-net-hyperlink" CssClass="anchor" Target="_blank"></asp:HyperLink>


        </div>
        <div class="p-3">
            <asp:RadioButton ID="radioButton1" Text="Male" GroupName="gender" runat="server" />

            <asp:RadioButton ID="radioButton2" Text="Female" GroupName="gender" runat="server" />
            <p>
                <asp:Button runat="server" Text="Submit" ID="radioSubmit" OnClick="radioSubmit_Click" CssClass="btn btn-secondary" />
            </p>

            <asp:Calendar runat="server" ID="Calendar" OnSelectionChanged="Calendar_SelectionChanged" ToolTip="date">
                <OtherMonthDayStyle ForeColor="LightGray"></OtherMonthDayStyle>

                <TitleStyle BackColor="Blue"
                    ForeColor="White"></TitleStyle>

                <DayStyle BackColor="gray"></DayStyle>

                <SelectedDayStyle BackColor="LightGray" ForeColor="Blue"
                    Font-Bold="True"></SelectedDayStyle>
            </asp:Calendar>


        </div>
        <div class="p-3">
            <asp:CheckBox runat="server" Text="Blue" ID="checkbox1" />
            <asp:CheckBox runat="server" Text="Red" ID="checkbox2" />
            <asp:CheckBox runat="server" Text="Yellow" ID="checkbox3" />

            <asp:Button ID="chechkBoxClick" CssClass="btn btn-secondary" runat="server" Text="Submit" OnClick="chechkBox_Click" />

            <div>
                <asp:Label runat="server" Text="Data list of given data: "></asp:Label>
                <asp:DataList runat="server" ID="dataList">
                    <ItemTemplate>
                        <table cellpadding="2" cellspacing="2" border="1" style="width: 400px; height: 100px; border: dashed 2px #04AFEF; background-color: #FFFFFF">
                            <tr>
                                <td>
                                    <b>First Name: </b><span class="city"><%# Eval("First Name") %></span><br />
                                    <b>Last Name: </b><span class="postal"><%# Eval("Last Name") %></span><br />
                                    <b>Email: </b><span class="country"><%# Eval("Email")%></span><br />
                                </td>
                            </tr>
                        </table>
                    </ItemTemplate>
                </asp:DataList>


                <asp:Label runat="server" Text="Data Grid of given data: "></asp:Label>
                <asp:DataGrid runat="server" ID="dataGrid">
                    
                </asp:DataGrid>
            </div>
        </div>
    </asp:Panel>
    <p runat="server" id="nameText"></p>
    <p runat="server" id="surnameText"></p>
    <p runat="server" id="emailText"></p>
    <asp:Label runat="server" ID="title" CssClass="title"></asp:Label>


    <asp:DataList ID="DataList1" runat="server"></asp:DataList>
    <script src="JsScripts/formScript.js"></script>
</asp:Content>
