import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExperienceProfileComponent } from './experience-profile.component';

describe('ExperienceProfileComponent', () => {
  let component: ExperienceProfileComponent;
  let fixture: ComponentFixture<ExperienceProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ExperienceProfileComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExperienceProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
