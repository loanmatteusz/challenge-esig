import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {
  LoginPayload,
  LoginResponse,
  RegisterPayload,
  RegisterResponse
} from '../interfaces/auth.interface';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) { }

  public login(data: LoginPayload): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, data);
  }

  public register(data: RegisterPayload): Observable<RegisterResponse> {
    return this.http.post<RegisterResponse>(`${this.apiUrl}/register`, data);
  }

  public setToken(token: string): void {
    localStorage.setItem('access_token', token);
  }

  public getToken(): string | null {
    return localStorage.getItem('access_token');
  }

  public isLoggedIn(): boolean {
    return !!this.getToken();
  }

  public logout(): void {
    localStorage.removeItem('access_token');
  }
}
