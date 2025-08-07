import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewstaffComponent } from './viewstaff.component';

describe('ViewstaffComponent', () => {
  let component: ViewstaffComponent;
  let fixture: ComponentFixture<ViewstaffComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewstaffComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewstaffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
