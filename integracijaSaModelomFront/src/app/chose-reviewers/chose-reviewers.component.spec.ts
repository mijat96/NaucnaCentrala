import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoseReviewersComponent } from './chose-reviewers.component';

describe('ChoseReviewersComponent', () => {
  let component: ChoseReviewersComponent;
  let fixture: ComponentFixture<ChoseReviewersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChoseReviewersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChoseReviewersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
