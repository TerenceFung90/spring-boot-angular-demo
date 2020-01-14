import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-image-search-panel',
  templateUrl: './image-search-panel.component.html',
  styleUrls: ['./image-search-panel.component.css']
})
export class ImageSearchPanelComponent implements OnInit {

  constructor(private fb: FormBuilder) { }

  @Output() 
  onSubmitFn: EventEmitter<any> = new EventEmitter();
  inputForm: FormGroup;

  ngOnInit() {
    this.inputForm = this.fb.group({
      autoCorrect: [true, Validators.required],
      pageNumber:[1, Validators.required],
      pageSize:[10, Validators.required],
      q:['test', Validators.required],
      safeSearch:[true, Validators.required]
    });

  }

  onSubmit(){
    this.onSubmitFn.emit(this.inputForm.getRawValue());
  }

}
