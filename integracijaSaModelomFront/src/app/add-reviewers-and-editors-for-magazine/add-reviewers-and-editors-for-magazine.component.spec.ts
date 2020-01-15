import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddReviewersAndEditorsForMagazineComponent } from './add-reviewers-and-editors-for-magazine.component';

describe('AddReviewersAndEditorsForMagazineComponent', () => {
  let component: AddReviewersAndEditorsForMagazineComponent;
  let fixture: ComponentFixture<AddReviewersAndEditorsForMagazineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddReviewersAndEditorsForMagazineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddReviewersAndEditorsForMagazineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
