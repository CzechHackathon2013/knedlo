//
//  DetailViewController.h
//  Knedlo
//
//  Created by Martin Wenisch on 10/19/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "FormatFilter.h"
#import "GTLKnedlo.h"

@interface DetailViewController : UIViewController

@property (nonatomic, strong) IBOutlet UIView *contentView;
@property (nonatomic, strong) IBOutlet UIImageView *iconView;

@property (nonatomic, strong) IBOutlet UIImageView *noiseView;

@property (nonatomic, strong) IBOutlet UILabel *titleLabel;
@property (nonatomic, strong) IBOutlet UILabel *descriptionLabel;
@property (nonatomic, strong) IBOutlet UITextView *articleView;

//data
@property (nonatomic, strong) GTLKnedloArticle *article;

- (IBAction)backButtonPressed:(id)sender;

@end
