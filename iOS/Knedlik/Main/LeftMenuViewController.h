//
//  LeftMenuViewController.h
//  Knedlo
//
//  Created by Martin Wenisch on 10/19/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "SourceCategory.h"

@interface LeftMenuViewController : UIViewController <UICollectionViewDataSource, UICollectionViewDelegate, UITableViewDataSource, UITableViewDelegate>

@property (nonatomic, strong) IBOutlet UIView *menuPull;

@property (nonatomic, weak) UIImageView *noiseView;

@property (nonatomic, strong) IBOutlet NSLayoutConstraint *pullBottom;
@property (nonatomic, strong) IBOutlet NSLayoutConstraint *sideConstraint;

@property (nonatomic, strong) IBOutlet UICollectionView *collectionView;
@property (nonatomic, strong) IBOutlet UITableView *tableView;

@property (nonatomic, weak) NSMutableArray *dataSource;

- (void)hideAnimated:(BOOL)animated;

@end
