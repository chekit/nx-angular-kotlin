import { HttpClient } from '@angular/common/http';
import { Component, inject, signal } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NxWelcome } from './nx-welcome';

@Component({
  imports: [NxWelcome, RouterModule],
  selector: 'app-root',
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {
  protected title = 'users';
  protected connectionStatus = signal<string>('')

  private readonly http = inject(HttpClient);



  checkConnection() {
    this.connectionStatus.set('')
    this.http.get('http://localhost:8080', {responseType: 'text'}).subscribe( result => this.connectionStatus.set(result))
  }
}
