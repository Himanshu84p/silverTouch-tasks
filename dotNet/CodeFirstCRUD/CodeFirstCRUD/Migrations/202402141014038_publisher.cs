namespace CodeFirstCRUD.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class publisher : DbMigration
    {
        public override void Up()
        {
            AlterColumn("dbo.Members", "Email", c => c.String(nullable: false));
            AlterColumn("dbo.Members", "Phone", c => c.String(nullable: false));
            AlterColumn("dbo.Publishers", "Email", c => c.String(nullable: false));
            AlterColumn("dbo.Publishers", "Phone", c => c.String(nullable: false));
        }
        
        public override void Down()
        {
            AlterColumn("dbo.Publishers", "Phone", c => c.String());
            AlterColumn("dbo.Publishers", "Email", c => c.String());
            AlterColumn("dbo.Members", "Phone", c => c.String());
            AlterColumn("dbo.Members", "Email", c => c.String());
        }
    }
}
