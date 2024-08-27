import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MySettingsComponent } from './mysettings.component';

describe('MysettingsComponent', () => {
  let component: MySettingsComponent;
  let fixture: ComponentFixture<MySettingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MySettingsComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(MySettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
