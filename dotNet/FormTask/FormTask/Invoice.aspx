<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Invoice.aspx.cs" Inherits="FormTask.Invoice" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Invoice Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
    <style>
        #InvoiceForm {
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
    <form id="InvoiceForm" runat="server">
        <h1>Invoice Details </h1>

        <div class="mb-1">
            <label for="inpOrderID" class="form-label">Order ID:</label>
            <asp:TextBox ID="inpOrderID" class="form-control" runat="server" TextMode="Number"></asp:TextBox>
            <asp:RequiredFieldValidator ForeColor="Red" runat="server" ControlToValidate="inpOrderID" ErrorMessage="Field Required" />
        </div>

        <div class="mb-3">
            <label for="inpAmountPaid">AmountPaid :</label>
            <asp:TextBox class="form-control" ID="inpAmountPaid" runat="server" TextMode="Number"></asp:TextBox>
            <asp:RequiredFieldValidator ForeColor="Red" runat="server" ControlToValidate="inpAmountPaid" ErrorMessage="Field Required" />
        </div>
        <div class="mb-3">
            <label for="inpPaymentDate">PaymentDate:</label>
            <asp:TextBox class="form-control" ID="inpPaymentDate" runat="server" TextMode="Date"></asp:TextBox>
            <asp:RequiredFieldValidator ForeColor="Red" runat="server" ControlToValidate="inpPaymentDate" ErrorMessage="Field Required" />
        </div>
        <div class="mb-3">
            <label for="inpPaymentMethod">PaymentMethod:</label>
            <asp:TextBox class="form-control" ID="inpPaymentMethod" runat="server"></asp:TextBox>
            <asp:RequiredFieldValidator ForeColor="Red" runat="server" ControlToValidate="inpPaymentMethod" ErrorMessage="Field Required" />
        </div>

        <div class="mb-3">
            <asp:Button CssClass="btn btn-primary" ID="btnSubmit" runat="server" Text="Submit" OnClick="btnSubmit_Click" />
            <asp:Button CssClass="btn btn-primary" ID="btnUpdate" runat="server" Text="Update" OnClick="btnUpdate_Click" Visible="false" />

            <asp:Button CssClass="btn btn-primary" ID="btnCancel" runat="server" Text="Cancel" OnClick="btnCancel_Click" Visible="false" />
        </div>


        <div style="margin: 100px 0 100px 0;">
            <asp:GridView ID="GridView1" CssClass="gridview" runat="server" AutoGenerateColumns="false" EmptyDataText="No records has been added."
                DataKeyNames="InvoiceID" OnRowEditing="OnRowEditing" OnRowUpdating="OnRowUpdating" OnRowCancelingEdit="OnRowCancelingEdit" OnRowDataBound="OnRowDataBound" OnRowDeleting="OnRowDeleting" OnPageIndexChanging="OnPaging" PageSize="5"
                AllowPaging="true">
                <Columns>
                    <asp:TemplateField HeaderText="OrderID" ItemStyle-Width="150">
                        <ItemTemplate>
                            <asp:Label ID="lblOrderID" runat="server" Text='<%# Eval("OrderID") %>'></asp:Label>
                        </ItemTemplate>
                        <EditItemTemplate>
                            <asp:TextBox ID="txtOrderID" runat="server" Text='<%# Eval("OrderID") %>' Width="140"></asp:TextBox>
                        </EditItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="AmountPaid" ItemStyle-Width="150">
                        <ItemTemplate>
                            <asp:Label ID="lblAmountPaid" runat="server" Text='<%# Eval("AmountPaid") %>'></asp:Label>
                        </ItemTemplate>
                        <EditItemTemplate>
                            <asp:TextBox ID="txtAmountPaid" runat="server" Text='<%# Eval("AmountPaid") %>' Width="140"></asp:TextBox>
                        </EditItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="PaymentDate" ItemStyle-Width="150">
                        <ItemTemplate>
                            <asp:Label ID="lblPaymentDate" runat="server" Text='<%# Eval("PaymentDate") %>'></asp:Label>
                        </ItemTemplate>
                        <EditItemTemplate>
                            <asp:TextBox TextMode="Date" ID="txtPaymentDate" runat="server" Text='<%# Eval("PaymentDate") %>' Width="140"></asp:TextBox>
                        </EditItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField HeaderText="PaymentMethod" ItemStyle-Width="150">
                        <ItemTemplate>
                            <asp:Label ID="lblPaymentMethod" runat="server" Text='<%# Eval("PaymentMethod") %>'></asp:Label>
                        </ItemTemplate>
                        <EditItemTemplate>
                            <asp:TextBox ID="txtPaymentMethod" runat="server" Text='<%# Eval("PaymentMethod") %>' Width="140"></asp:TextBox>
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

