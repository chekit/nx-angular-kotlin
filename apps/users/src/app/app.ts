import { JsonPipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, signal } from '@angular/core';
import { RouterModule } from '@angular/router';

interface UserData {name: string; role: string;}

@Component({
  imports: [RouterModule, JsonPipe],
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {
  protected connectionStatus = signal<UserData| null>(null)

  private readonly http = inject(HttpClient);

  checkConnection() {
    this.connectionStatus.set(null)
    this.http.get<UserData>('http://localhost:8080').subscribe( result => this.connectionStatus.set(result))
  }
}
