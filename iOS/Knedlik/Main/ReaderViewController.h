//
//  ReaderViewController.h
//  Knedlik
//
//  Created by Martin Wenisch on 10/19/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <GPUImage/GPUImage.h>
#import "GPUImageMask.h"
#import "FormatFilter.h"
#import "LeftMenuViewController.h"
#import <GTLObject.h>
#import "GTLKnedlo.h"

@interface ReaderViewController : UIViewController <UICollectionViewDataSource, UICollectionViewDelegate>

@property (nonatomic, strong) IBOutlet UICollectionView *collectionView;

@property (nonatomic, strong) IBOutlet UIImageView *noiseView;

@property (nonatomic, strong) NSMutableArray *dataSource;

@end
