<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ServicesForm.aspx.cs" Inherits="FormTask.ServicesForm" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Services Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />

    <style>
        #ServiceForm {
            display: flex;
            flex-direction: column;
            row-gap: 1rem;
            width: auto;
            justify-content: center;
            align-items: center;
            padding: 2rem;
            background-image: linear-gradient(to bottom, #d9befe, #a0c4ff, #ffffff);
        }

        .form-control {
            width: 300px;
        }

        .gridview {
            border-collapse: collapse;
            width: 100%;
            width: 100%;
            background-color: #ffffff;
            border: none;
            box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.4);
        }

            .gridview th {
                padding: 10px;
                text-align: left;
                background-color: #f2f2f2;
                border-bottom: 1px solid #dddddd;
            }

            /* Style for GridView data rows */
            .gridview tr {
                border-bottom: 1px solid #dddddd;
            }

                /* Style for alternating data rows */
                .gridview tr:nth-child(even) {
                    background-color: #f9f9f9;
                }

            .gridview td {
                padding: 10px;
            }

            .gridview .editItemTemplate {
                width: 100%;
                padding: 5px;
                box-sizing: border-box;
                border: 1px solid #cccccc;
            }

            .gridview .commandField {
                padding: 5px 10px;
                background-color: #007bff;
                color: #ffffff;
                border: none;
                cursor: pointer;
                border-radius: 5px;
                text-decoration: none;
            }

            .gridview .deleteBtn {
                background-color: indianred;
            }

            .gridview .commandField:hover {
                background-color: #0056b3;
            }

            .gridview .deleteBtn:hover {
                background-color: #8b4b4b;
            }

            .gridview .commandField:focus {
                outline: none;
            }

            .gridview .commandField:active {
                background-color: #0056b3;
            }
    </style>
</head>
<body>
    <form id="ServiceForm" runat="server">
        <h1>Services Details</h1>
        <asp:TextBox runat="server" ID="tempID" Visible="false"></asp:TextBox>
        <div class="mb-3">
            <label for="inpServiceName" class="form-label">Service Name :</label>
            <asp:TextBox ID="inpServiceName" class="form-control" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ForeColor="Red" runat="server" ControlToValidate="inpServiceName" ErrorMessage="Field Required" />
        </div>
        <div class="mb-3">
            <label for="inpDepartmentID">Department ID:</label>
            <asp:DropDownList class="form-control" ID="inpDepartmentID" runat="server">

                <asp:ListItem Value="1" Text="1"></asp:ListItem>
                <asp:ListItem Value="2" Text="2"></asp:ListItem>
                <asp:ListItem Value="3" Text="3"></asp:ListItem>
                <asp:ListItem Value="4" Text="4"></asp:ListItem>
                <asp:ListItem Value="5" Text="5"></asp:ListItem>
            </asp:DropDownList>
            <asp:RequiredFieldValidator ForeColor="Red" runat="server" ControlToValidate="inpDepartmentID" ErrorMessage="Field Required" />
        </div>
        <div class="mb-3">
            <label for="inpDescription">Description :</label>
            <asp:TextBox class="form-control" ID="inpDescription" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ForeColor="Red" runat="server" ControlToValidate="inpDescription" ErrorMessage="Field Required" />
        </div>
        <div class="mb-3">
            <label for="inpPrice">Price :</label>
            <asp:TextBox class="form-control" ID="inpPrice" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ForeColor="Red" runat="server" ControlToValidate="inpPrice" ErrorMessage="Field Required" />
        </div>

        <div class="mb-3">
            <label for="inpDate">Date :</label>
            <asp:TextBox TextMode="Date" CssClass="form-control" runat="server" ID="inpDate"></asp:TextBox>
            <asp:RequiredFieldValidator ForeColor="Red" runat="server" ControlToValidate="inpDate" ErrorMessage="Field Required" />
        </div>

        <div class="mb-3">
            <label for="inpAvailable">Available:</label>
            <asp:TextBox class="form-control" ID="inpAvailable" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ForeColor="Red" runat="server" ControlToValidate="inpAvailable" ErrorMessage="Field Required" />
        </div>
        <div class="mb-3">
            <asp:Button CssClass="btn btn-primary" ID="btnSubmit" runat="server" Text="Submit" OnClick="btnSubmit_Click" />
            <asp:Button CssClass="btn btn-primary" ID="btnUpdate" runat="server" Text="Update" OnClick="btnUpdate_Click" Visible="false" />

            <asp:Button CssClass="btn btn-primary" CausesValidation="false" ID="btnCancel" runat="server" Text="Cancel" OnClick="btnCancel_Click" Visible="false" />
        </div>


        <div style="margin-top: 100px;">
            <asp:GridView ID="GridView1" CssClass="gridview" runat="server" AutoGenerateColumns="false" EmptyDataText="No records has been added." DataKeyNames="ServiceID" OnRowEditing="OnRowEditing" OnRowDataBound="OnRowDataBound" OnRowDeleting="OnRowDeleting" OnPageIndexChanging="OnPaging" PageSize="5"
                AllowPaging="true">
                <Columns>
                    <asp:TemplateField HeaderText="ServiceName" ItemStyle-Width="150">
                        <ItemTemplate>
                            <asp:Label ID="lblServiceName" runat="server" Text='<%# Eval("ServiceName") %>'></asp:Label>
                        </ItemTemplate>

                    </asp:TemplateField>

                    <asp:TemplateField HeaderText="DepartmentID" ItemStyle-Width="150">
                        <ItemTemplate>
                            <asp:Label ID="lblDepartmentID" runat="server" Text='<%# Eval("DepartmentID") %>'></asp:Label>
                        </ItemTemplate>
                    </asp:TemplateField>

                    <asp:TemplateField HeaderText="Description" ItemStyle-Width="250">
                        <ItemTemplate>
                            <asp:Label ID="lblDescription" runat="server" Text='<%# Eval("Description") %>'></asp:Label>
                        </ItemTemplate>
                    </asp:TemplateField>

                    <asp:TemplateField HeaderText="Price" ItemStyle-Width="150">
                        <ItemTemplate>
                            <asp:Label ID="lblPrice" runat="server" Text='<%# Eval("Price") %>'></asp:Label>
                        </ItemTemplate>

                    </asp:TemplateField>

                    <asp:TemplateField HeaderText="Date" ItemStyle-Width="150">
                        <ItemTemplate>
                            <asp:Label ID="lblDate" runat="server" Text='<%# Eval("Date") %>'></asp:Label>
                        </ItemTemplate>

                    </asp:TemplateField>

                    <asp:TemplateField HeaderText="Available" ItemStyle-Width="150">
                        <ItemTemplate>
                            <asp:Label ID="lblAvailable" runat="server" Text='<%# Eval("Available") %>'></asp:Label>
                        </ItemTemplate>

                    </asp:TemplateField>

                    <asp:CommandField ControlStyle-CssClass="commandField" CausesValidation="false" ButtonType="Link" ShowEditButton="true"
                        ItemStyle-Width="100" />
                    <asp:CommandField ControlStyle-CssClass="commandField deleteBtn" CausesValidation="false" ButtonType="Link" ShowDeleteButton="true"
                        ItemStyle-Width="100" />
                </Columns>
            </asp:GridView>
        </div>
    </form>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
