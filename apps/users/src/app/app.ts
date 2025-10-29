import { JsonPipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, signal } from '@angular/core';
// eslint-disable-next-line @nx/enforce-module-boundaries
import { User } from './../../../../backend/apps/users-backend/src/main/kotlin/nl/rabobank/usersbackend/entity/User';
// eslint-disable-next-line @nx/enforce-module-boundaries
import { Role } from './../../../../backend/apps/users-backend/src/main/kotlin/nl/rabobank/usersbackend/entity/Role';

@Component({
  imports: [JsonPipe],
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {
  protected users = signal<User[] | null>(null);
  protected roles = signal<Role[] | null>(null);

  private readonly http = inject(HttpClient);

  getUsers() {
    this.users.set(null);
    this.http
      .get<User[]>('http://localhost:8080/api/users')
      .subscribe((result) => this.users.set(result));
  }

  getRoles() {
    this.users.set(null);
    this.http
      .get<Role[]>('http://localhost:8080/api/roles')
      .subscribe((result) => this.roles.set(result));
  }
}
