import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MasterEditorCheckComponent } from './master-editor-check.component';

describe('MasterEditorCheckComponent', () => {
  let component: MasterEditorCheckComponent;
  let fixture: ComponentFixture<MasterEditorCheckComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MasterEditorCheckComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MasterEditorCheckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
