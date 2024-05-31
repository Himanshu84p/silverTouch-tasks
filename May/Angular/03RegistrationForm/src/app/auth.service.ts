import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/v1/users';

  constructor(private http: HttpClient) { }

  //Register user
  register(user: any): Observable<any> {
    console.log('user is here', user);
    return this.http.post(
      `${this.apiUrl}/register`,
      user,
      this.getHttpOptions()
    );
  }

  //login User
  login(user: any): Observable<any> {
    return this.http
      .post(`${this.apiUrl}/login`, user, this.getHttpOptions())
      .pipe(
        map((response: any) => {
          if (response.success) {
            // Save access token to memory or localStorage
            if (response.message.accessToken) {
              localStorage.setItem(
                'accessToken',
                response.message.accessToken
              );
              let currUser = JSON.stringify(response.message.user);
              localStorage.setItem('user', currUser);
            }
            // Save refresh token to httpOnly cookie
            if (response.message.refreshToken) {
              console.log('refreshtoken saving');

              this.setRefreshToken(response.message.refreshToken);
            }
          }

          return response;
        })
      );
  }

  //logout user
  logout(): void {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('user');
    this.deleteRefreshToken();
  }

  // Check if user is logged in
  isLoggedIn(): boolean {
    return !!localStorage.getItem('accessToken');
  }

  // Get token from local storage
  getAccessToken(): string | null {
    return localStorage.getItem('accessToken');
  }

  //set refresh tojen as httpOnly cookie
  private setRefreshToken(refreshToken: string): void {
    document.cookie = `refreshToken=${refreshToken}; Path=/; secure; httpOnly`;
  }
  //delete refreshToken
  private deleteRefreshToken(): void {
    document.cookie = `refreshToken=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
  }

  //HTTP Options with authorization header
  private getHttpOptions() {
    const token = this.getAccessToken();
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    if (token) {
      headers = headers.append('Authorization', `Bearer ${token}`);
    }
    return { headers: headers };
  }
}
