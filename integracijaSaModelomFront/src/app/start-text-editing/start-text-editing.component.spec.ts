import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StartTextEditingComponent } from './start-text-editing.component';

describe('StartTextEditingComponent', () => {
  let component: StartTextEditingComponent;
  let fixture: ComponentFixture<StartTextEditingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StartTextEditingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StartTextEditingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
