//
//  DetailMenuViewController.h
//  Knedlo
//
//  Created by Martin Wenisch on 10/20/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DetailMenuViewController : UIViewController

@property (nonatomic, strong) IBOutlet UIView *menuPull;

@property (nonatomic, weak) UIImageView *noiseView;

@property (nonatomic, strong) IBOutlet NSLayoutConstraint *sideConstraint;

- (void)hideAnimated:(BOOL)animated;

@end
