//
//  ReaderCell.h
//  Knedlik
//
//  Created by Martin Wenisch on 10/19/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <GPUImage/GPUImage.h>

@interface ReaderCell : UICollectionViewCell <UIGestureRecognizerDelegate>

@property (nonatomic, strong) IBOutlet UIView *contentView;
@property (nonatomic, strong) IBOutlet UIView *trashView;

@property (nonatomic, strong) IBOutlet NSLayoutConstraint *trashViewLeadingSpace;
@property (nonatomic, strong) IBOutlet NSLayoutConstraint *addViewTrailingSpace;
@property (nonatomic, strong) IBOutlet NSLayoutConstraint *contentViewLeadingSpace;
@property (nonatomic, strong) IBOutlet NSLayoutConstraint *contentViewTrailingSpace;

@property (nonatomic, weak) UICollectionView *collectionView;
@property (nonatomic, weak) NSMutableArray *dataSource;

@property (nonatomic, strong) IBOutlet UIImageView *smallIconView;
@property (nonatomic, strong) IBOutlet UIImageView *bigImageView;

@property (nonatomic, strong) IBOutlet UILabel *titleLabel;
@property (nonatomic, strong) IBOutlet UILabel *descriptionLabel;

- (void)panGestureMoved:(UIPanGestureRecognizer *)recognizer;

@end
