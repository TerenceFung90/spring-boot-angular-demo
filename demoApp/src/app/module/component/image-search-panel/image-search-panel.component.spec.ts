import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageSearchPanelComponent } from './image-search-panel.component';

describe('ImageSearchPanelComponent', () => {
  let component: ImageSearchPanelComponent;
  let fixture: ComponentFixture<ImageSearchPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImageSearchPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImageSearchPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
