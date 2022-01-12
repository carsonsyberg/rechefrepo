import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnterIngredientsComponent } from './enter-ingredients.component';

describe('EnterIngredientsComponent', () => {
  let component: EnterIngredientsComponent;
  let fixture: ComponentFixture<EnterIngredientsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnterIngredientsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnterIngredientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
