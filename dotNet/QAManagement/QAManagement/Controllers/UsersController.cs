using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Data;
using System.Data.Entity;
using System.IO;
using System.Linq;
using System.Net;
using System.Security.Cryptography;
using System.Text;
using System.Web;
using System.Web.Helpers;
using System.Web.Mvc;
using QAManagement.Filters;
using QAManagement.Models;

namespace QAManagement.Controllers
{
    
    public class UsersController : Controller
    {
        private QAManagementEntities db = new QAManagementEntities();

        // GET: Users
        [RoleAuthorization("Admin")]
        public ActionResult Index()
        {
            return View(db.Users.ToList());
        }

        // GET: Users/Details/5
        [RoleAuthorization("Admin")]
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            User user = db.Users.Find(id);
            if (user == null)
            {
                return HttpNotFound();
            }
            return View(user);
        }

        // GET: Users/Create
        [RoleAuthorization("Admin")]
        public ActionResult Create()
        {
            return View();
        }

        // POST: Users/Create
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "UserID,Username,PasswordHash,Email,Role")] User user)
        {
            if (ModelState.IsValid)
            {
                user.PasswordHash = EncryptString(user.PasswordHash);
                db.Users.Add(user);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(user);
        }

        // GET: Users/Login
        public ActionResult LogIn()
        {

            return View();
        }

        // POST: Users/Login
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Login(User user)
        {
                var reqUser = db.Users.FirstOrDefault(u => u.Email == user.Email);
                string decryptPass = DecryptString(reqUser.PasswordHash);
                // Check if user exists and password matches
                if (user != null && decryptPass == user.PasswordHash && user.Email == reqUser.Email)
                {
                    // Successful login, redirect to index or dashboard
                    //cheking the role
                    if (reqUser.Role == "Admin")
                    {
                        Session["UserId"] = reqUser.UserID;
                        Session["UserRole"] = reqUser.Role;
                        Session["Username"] = reqUser.Username;
                        return RedirectToAction("Index", "Admin");
                    }
                    else if (reqUser.Role == "Teacher")
                    {
                        Session["UserId"] = reqUser.UserID;
                        Session["UserRole"] = reqUser.Role;
                        Session["Username"] = reqUser.Username;
                        return RedirectToAction("Index", "Teacher");
                    }
                    else if (reqUser.Role == "Student")
                    {
                        Session["UserId"] = reqUser.UserID;
                        Session["UserRole"] = reqUser.Role;
                        Session["Username"] = reqUser.Username;
                        return RedirectToAction("Index", "Student");
                    }


                }
                else
                {
                    // Invalid email or password, add error message to ModelState
                    ModelState.AddModelError("", "Invalid email or password.");
                }
            return View();
        }

        
        private string EncryptString(string plainText)
        {
            const string EncryptionKey = "qWE7&5pZ@2#9Df!1gH*3sKl$8oP5mN^0";

            using (Aes aesAlg = Aes.Create())
            {
                aesAlg.Key = Encoding.UTF8.GetBytes(EncryptionKey);
                aesAlg.IV = new byte[16];

                ICryptoTransform encryptor = aesAlg.CreateEncryptor(aesAlg.Key, aesAlg.IV);

                using (var msEncrypt = new System.IO.MemoryStream())
                {
                    using (var csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write))
                    {
                        using (var swEncrypt = new System.IO.StreamWriter(csEncrypt))
                        {
                            swEncrypt.Write(plainText);
                        }
                    }
                    return Convert.ToBase64String(msEncrypt.ToArray());
                }
            }
        }

        public static string DecryptString(string cipherText)
        {
            const string EncryptionKey = "qWE7&5pZ@2#9Df!1gH*3sKl$8oP5mN^0";
            using (Aes aesAlg = Aes.Create())
            {
                aesAlg.Key = Encoding.UTF8.GetBytes(EncryptionKey);
                aesAlg.IV = new byte[16];

                ICryptoTransform decryptor = aesAlg.CreateDecryptor(aesAlg.Key, aesAlg.IV);

                byte[] cipherBytes = Convert.FromBase64String(cipherText);

                using (MemoryStream msDecrypt = new MemoryStream(cipherBytes))
                {
                    using (CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read))
                    {
                        using (StreamReader srDecrypt = new StreamReader(csDecrypt))
                        {
                            return srDecrypt.ReadToEnd();
                        }
                    }
                }
            }
        }

        public ActionResult Logout()
        {
            Session.Clear(); 
            return RedirectToAction("Login"); // Redirect to login page after logout
        }

        [RoleAuthorization("Admin")]
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            User EditedUser = db.Users.Find(id);
            if (EditedUser == null)
            {
                return HttpNotFound();
            }
            
            return View(EditedUser);
        }

        // POST: QuestionPapers/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit(User editedUser)
        {
            if (ModelState.IsValid)
            {
                
                db.Entry(editedUser).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            
            return View(editedUser);
        }

        [RoleAuthorization("Admin")]
        public ActionResult Delete(int? id)
        {
            if(id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            User DeletedUser = db.Users.Find(id);
            if (DeletedUser == null)
            {
                return HttpNotFound();
            }

            return View(DeletedUser);
        }

        // POST: QuestionPapers/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            //removing answers of this user
            var userAnswers = db.Answers.Where(q => q.UserID == id);
            foreach (var ans in userAnswers)
            {
                db.Answers.Remove(ans);
            }
            db.SaveChanges();

            

            var userQuestionPapers = db.QuestionPapers.Where(q => q.CreatorID == id);

            foreach (var que in userQuestionPapers)
            {
                
                //removing questions of this que
                var Questions = db.Questions.Where(q => q.QuestionPaperID == que.QuestionPaperID);
                foreach (var oneQue in Questions)
                {
                    var AnsQuestion = db.Answers.Where(q => q.QuestionID == oneQue.QuestionID);
                    foreach (var item in AnsQuestion)
                    {
                        db.Answers.Remove(item);
                    }
                    db.Questions.Remove(oneQue);
                }
                
                db.QuestionPapers.Remove(que);
            }
            db.SaveChanges();

            //removing the user
            User user = db.Users.Find(id);
            db.Users.Remove(user);
            db.SaveChanges();
            return RedirectToAction("Index");
        }


        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
