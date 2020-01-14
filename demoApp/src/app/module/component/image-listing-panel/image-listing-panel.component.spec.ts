import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageListingPanelComponent } from './image-listing-panel.component';

describe('ImageListingPanelComponent', () => {
  let component: ImageListingPanelComponent;
  let fixture: ComponentFixture<ImageListingPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImageListingPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImageListingPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
