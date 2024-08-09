import { Component, OnInit } from '@angular/core';
import { AlertService } from '../alert.service';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.scss']
})
export class AlertComponent implements OnInit {
  type: string = '';
  message: string = '';

  constructor(private alertService: AlertService) {}

  ngOnInit(): void {
    // Subscribe to the alert observable to update the alert type and message
    this.alertService.alert$.subscribe(alert => {
      this.type = alert.type;
      this.message = alert.message;
    });
  }
}
