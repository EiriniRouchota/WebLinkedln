import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  private alertSubject = new Subject<{ type: string, message: string }>();
  alert$: Observable<{ type: string, message: string }> = this.alertSubject.asObservable();

  showAlert(type: string, message: string) {
    this.alertSubject.next({ type, message });
  }
}
