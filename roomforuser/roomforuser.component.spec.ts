import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomforuserComponent } from './roomforuser.component';

describe('RoomforuserComponent', () => {
  let component: RoomforuserComponent;
  let fixture: ComponentFixture<RoomforuserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RoomforuserComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RoomforuserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
