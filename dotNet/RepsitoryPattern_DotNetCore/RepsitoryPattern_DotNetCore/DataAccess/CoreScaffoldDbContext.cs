using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;

namespace RepsitoryPattern_DotNetCore.DataAccess;

public partial class CoreScaffoldDbContext : DbContext
{
    public CoreScaffoldDbContext(DbContextOptions<CoreScaffoldDbContext> options)
        : base(options)
    {
    }

    public virtual DbSet<Employee> Employees { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Employee>(entity =>
        {
            entity.HasKey(e => e.Id).HasName("PK__Employee__3213E83FCD5AA842");

            entity.ToTable("Employee");

            entity.Property(e => e.Id).HasColumnName("id");
            entity.Property(e => e.Department)
                .HasMaxLength(100)
                .IsUnicode(false)
                .HasColumnName("department");
            entity.Property(e => e.FirstName)
                .HasMaxLength(50)
                .IsUnicode(false)
                .HasColumnName("firstName");
            entity.Property(e => e.JobTitle)
                .HasMaxLength(100)
                .IsUnicode(false)
                .HasColumnName("jobTitle");
            entity.Property(e => e.LastName)
                .HasMaxLength(50)
                .IsUnicode(false)
                .HasColumnName("lastName");
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
