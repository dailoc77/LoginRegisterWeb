import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeNewPasswordPageComponent } from './change-new-password-page.component';

describe('ChangeNewPasswordPageComponent', () => {
  let component: ChangeNewPasswordPageComponent;
  let fixture: ComponentFixture<ChangeNewPasswordPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChangeNewPasswordPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChangeNewPasswordPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
