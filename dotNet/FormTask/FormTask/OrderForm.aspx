﻿<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="OrderForm.aspx.cs" Inherits="FormTask.OrderForm" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Order Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
    <style>
        #OrderForm {
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
    <form id="OrderForm" runat="server">
        <h1>Order Details </h1>

        <div class="mb-1">
            <label for="inpCustomerID" class="form-label">Customer ID:</label>
            <asp:TextBox ID="inpCustomerID" class="form-control" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ForeColor="Red" runat="server" ControlToValidate="inpCustomerID" ErrorMessage="Field Required" />
        </div>

        <div class="mb-3">
            <label for="inpServiceID">Service ID:</label>
            <asp:TextBox class="form-control" ID="inpServiceID" runat="server" TextMode="Number"></asp:TextBox>
            <asp:RequiredFieldValidator ForeColor="Red" runat="server" ControlToValidate="inpServiceID" ErrorMessage="Field Required" />
        </div>
        <div class="mb-3">
            <label for="inpOrderDate">OrderDate:</label>
            <asp:TextBox class="form-control" ID="inpOrderDate" runat="server" TextMode="Date"></asp:TextBox>
            <asp:RequiredFieldValidator ForeColor="Red" runat="server" ControlToValidate="inpOrderDate" ErrorMessage="Field Required" />
        </div>
        <div class="mb-3">
            <label for="inpTotalAmount">TotalAmount:</label>
            <asp:TextBox class="form-control" TextMode="Number" ID="inpTotalAmount" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ForeColor="Red" runat="server" ControlToValidate="inpTotalAmount" ErrorMessage="Field Required" />
        </div>

        <div class="mb-3">
            <label for="inpPending">Pending :</label>
            <asp:RadioButtonList ID="inpPending" runat="server">
                <asp:ListItem Text="YES" Value="YES"></asp:ListItem>
                <asp:ListItem Text="NO" Value="NO"></asp:ListItem>
            </asp:RadioButtonList>

            <asp:RequiredFieldValidator ForeColor="Red" runat="server" ControlToValidate="inpTotalAmount" ErrorMessage="Field Required" />
        </div>

        <div class="mb-3">
            <asp:Button CssClass="btn btn-primary" ID="btnSubmit" runat="server" Text="Submit" OnClick="btnSubmit_Click" />
            <asp:Button CssClass="btn btn-primary" ID="btnUpdate" runat="server" Text="Update" OnClick="btnUpdate_Click" Visible="false" />

            <asp:Button CssClass="btn btn-primary" ID="btnCancel" runat="server" Text="Cancel" OnClick="btnCancel_Click" Visible="false" />
        </div>


        <div style="margin-top:100px 0 100px 0;">
            <asp:GridView ID="GridView1" CssClass="gridview" runat="server" AutoGenerateColumns="false" EmptyDataText="No records has been added."
                DataKeyNames="OrderID" OnRowEditing="OnRowEditing" OnRowDataBound="OnRowDataBound" OnRowDeleting="OnRowDeleting" OnPageIndexChanging="OnPaging" PageSize="5"
                AllowPaging="true">
                <Columns>
                    <asp:TemplateField HeaderText="CustomerID" ItemStyle-Width="150">
                        <ItemTemplate>
                            <asp:Label ID="lblCustomerID" runat="server" Text='<%# Eval("CustomerID") %>'></asp:Label>
                        </ItemTemplate>
                        <EditItemTemplate>
                            <asp:TextBox ID="txtCustomerID" runat="server" Text='<%# Eval("CustomerID") %>' Width="140"></asp:TextBox>
                        </EditItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="ServiceID" ItemStyle-Width="150">
                        <ItemTemplate>
                            <asp:Label ID="lblServiceID" runat="server" Text='<%# Eval("ServiceID") %>'></asp:Label>
                        </ItemTemplate>
                        <EditItemTemplate>
                            <asp:TextBox ID="txtServiceID" runat="server" Text='<%# Eval("ServiceID") %>' Width="140"></asp:TextBox>
                        </EditItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="Date" ItemStyle-Width="150">
                        <ItemTemplate>
                            <asp:Label ID="lblOrderDate" runat="server" Text='<%# Eval("OrderDate") %>'></asp:Label>
                        </ItemTemplate>
                        <EditItemTemplate>
                            <asp:TextBox ID="txtOrderDate" runat="server" Text='<%# Eval("OrderDate") %>' Width="140"></asp:TextBox>
                        </EditItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="TotalAmount" ItemStyle-Width="150">
                        <ItemTemplate>
                            <asp:Label ID="lblTotalAmount" runat="server" Text='<%# Eval("TotalAmount") %>'></asp:Label>
                        </ItemTemplate>
                        <EditItemTemplate>
                            <asp:TextBox ID="txtTotalAmount" runat="server" Text='<%# Eval("TotalAmount") %>' Width="140"></asp:TextBox>
                        </EditItemTemplate>
                    </asp:TemplateField>

                    <asp:TemplateField HeaderText="Pending" ItemStyle-Width="150">
                        <ItemTemplate>
                            <asp:Label ID="lblPending" runat="server" Text='<%# Eval("Pending") %>'></asp:Label>
                        </ItemTemplate>
                        <EditItemTemplate>
                            <asp:TextBox ID="txtPending" runat="server" Text='<%# Eval("Pending") %>' Width="140"></asp:TextBox>
                        </EditItemTemplate>
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
