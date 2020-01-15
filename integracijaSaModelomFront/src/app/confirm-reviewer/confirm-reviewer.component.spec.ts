import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmReviewerComponent } from './confirm-reviewer.component';

describe('ConfirmReviewerComponent', () => {
  let component: ConfirmReviewerComponent;
  let fixture: ComponentFixture<ConfirmReviewerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmReviewerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmReviewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
