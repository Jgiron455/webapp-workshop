import { TestBed } from '@angular/core/testing';

import { InventoryViewService } from './inventory-view.service';

describe('InventoryViewService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InventoryViewService = TestBed.get(InventoryViewService);
    expect(service).toBeTruthy();
  });
});
