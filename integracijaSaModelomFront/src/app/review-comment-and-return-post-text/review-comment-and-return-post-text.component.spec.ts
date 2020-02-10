import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewCommentAndReturnPostTextComponent } from './review-comment-and-return-post-text.component';

describe('ReviewCommentAndReturnPostTextComponent', () => {
  let component: ReviewCommentAndReturnPostTextComponent;
  let fixture: ComponentFixture<ReviewCommentAndReturnPostTextComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewCommentAndReturnPostTextComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewCommentAndReturnPostTextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
