namespace TodoApp.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class initialmodel : DbMigration
    {
        public override void Up()
        {
            AlterColumn("dbo.TodoItems", "Task", c => c.String(nullable: false, maxLength: 100));
        }
        
        public override void Down()
        {
            AlterColumn("dbo.TodoItems", "Task", c => c.String(nullable: false));
        }
    }
}
